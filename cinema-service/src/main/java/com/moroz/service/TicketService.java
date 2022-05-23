package com.moroz.service;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketStatusRepository ticketStatusRepository;

    private final MovieShowService movieShowService;

    public List<TicketDTO> getTickets() {
        List<TicketEntity> entities = ticketRepository.findAll();

        List<TicketDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(TicketEntityToDTOParser.parse(x)));

        log.info("Found all tickets");

        return dtoList;
    }

    public List<TicketDTO> getTicketsByStatusName(String ticketStatusName) {

        List<TicketEntity> entities = ticketRepository
                .findAllByTicketStatus(ticketStatusRepository.findByName(ticketStatusName).get());

        List<TicketDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(TicketEntityToDTOParser.parse(x)));

        return dtoList;
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

        TicketEntity ticketEntity = new TicketEntity(movieShowEntity, row, place, ticketStatusRepository.findByName("NEW").get());

        if (ticketEntity
                .equals(ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place).get())) {
            log.error("Place and row already occupied", new OccupiedRowAndPlaceException());
            throw new OccupiedRowAndPlaceException("Place and row already occupied");
        }

        ticketRepository.insertTicket(ticketEntity.getMovieShowEntity().getId(),
                ticketEntity.getRow(), ticketEntity.getPlace());

        log.info("Saved " + ticketEntity);

        return TicketEntityToDTOParser.parse(ticketEntity);
    }

    public TicketDTO updateTicket(Long movieShowId, int row, int place, String ticketStatusName) {
        MovieShowEntity movieShowEntity = movieShowService.getMovieShowRepository().findById(movieShowId)
                .orElseThrow(() -> new MovieShowNotFoundException("Movie Show not found"));

        TicketEntity ticketEntity = ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketEntity.setTicketStatusEntity(ticketStatusRepository.findByName(ticketStatusName).get());
        ticketEntity.setModificationDate(LocalDateTime.now());

        ticketRepository.save(ticketEntity);

        log.info("Updated " + ticketEntity);

        return TicketEntityToDTOParser.parse(ticketEntity);
    }

    public void deleteTicketById(Long id) {
        TicketEntity entity = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticketRepository.delete(entity);
        log.info("Deleted ticket by id " + id);
    }
}
