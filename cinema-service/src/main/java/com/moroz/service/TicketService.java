package com.moroz.service;

import com.moroz.cardgenerator.RandomCreditCardNumberGenerator;
import com.moroz.exceptions.MovieShowNotFoundException;
import com.moroz.exceptions.OccupiedRowAndPlaceException;
import com.moroz.exceptions.TicketNotFoundException;
import com.moroz.model.TicketDTO;
import com.moroz.parsers.TicketEntityToDTOParser;
import com.moroz.persistence.entities.MovieShowEntity;
import com.moroz.persistence.entities.TicketEntity;
import com.moroz.persistence.repo.TicketRepository;
import com.moroz.persistence.repo.TicketStatusRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TicketService {
    @Getter
    private TicketRepository ticketRepository;

    private final MovieShowService movieShowService;
    private final TicketStatusService ticketStatusService;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<TicketDTO> getTickets() {
        List<TicketEntity> entities = ticketRepository.findAll();

        List<TicketDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(TicketEntityToDTOParser.parse(x)));

        log.info("Found all tickets");

        return dtoList;
    }

    public List<TicketEntity> getProcessingTickets() {
        return ticketRepository.findAllByTicketStatusEntity(ticketStatusService.getStatusByName("PROCESSING"))
                .stream()
                .filter(x -> x.getPaymentId() != null)
                .collect(Collectors.toList());
    }

    public TicketDTO getTicketById(Long id) {
        TicketEntity entity = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        log.info("Found " + entity);

        return TicketEntityToDTOParser.parse(entity);
    }

    public TicketDTO addTicket(Long movieShowId, int row, int place) {
        MovieShowEntity movieShowEntity = movieShowService.getMovieShowRepository().findById(movieShowId)
                .orElseThrow(() -> new MovieShowNotFoundException("Movie show not found"));

        TicketEntity ticketEntity = new TicketEntity(movieShowEntity, row, place, ticketStatusService.getStatusByName("NEW"));

        if (row == 0 || place == 0
                || row > movieShowEntity.getCinemaEntity().getRowsAmount()
                || place > movieShowEntity.getCinemaEntity().getPlacesPerRowAmount()) {
            throw new RuntimeException("Wrong value of row and place");
        }

        ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place)
                .ifPresent(val -> {
                    throw new OccupiedRowAndPlaceException("Place and row already Occupied");
                });


        ticketRepository.insertTicket(ticketEntity.getMovieShowEntity().getId(),
                ticketEntity.getRow(), ticketEntity.getPlace());

        TicketEntity newTicketEntity = ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place).orElseThrow();

        newTicketEntity.setTicketStatusEntity(ticketStatusService.getStatusByName("PROCESSING"));
        newTicketEntity.setModificationDate(LocalDateTime.now());

        ticketRepository.save(newTicketEntity);

        log.info("Saved " + newTicketEntity);

        return TicketEntityToDTOParser.parse(newTicketEntity);
    }

    public TicketDTO setPaymentId(Long ticketId, Long paymentId) {
        TicketEntity entity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        entity.setPaymentId(paymentId);

        ticketRepository.save(entity);



        return TicketEntityToDTOParser.parse(entity);
    }

    public TicketDTO updateTicket(Long movieShowId, int row, int place, String ticketStatusName) {
        MovieShowEntity movieShowEntity = movieShowService.getMovieShowRepository().findById(movieShowId)
                .orElseThrow(() -> new MovieShowNotFoundException("Movie Show not found"));

        TicketEntity ticketEntity = ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketEntity.setTicketStatusEntity(ticketStatusService.getStatusByName(ticketStatusName));
        ticketEntity.setModificationDate(LocalDateTime.now());

        TicketEntity newTicketEntity = ticketRepository.save(ticketEntity);

        log.info("Updated " + ticketEntity);

        return TicketEntityToDTOParser.parse(newTicketEntity);
    }

    /*public TicketDTO receivePaymentStatusAndUpdateTicketStatus(JSONObject jsonObject) {

    }*/

    public void deleteTicketById(Long id) {
        TicketEntity entity = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticketRepository.delete(entity);
        log.info("Deleted ticket by id " + id);
    }
}
