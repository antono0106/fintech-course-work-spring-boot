package com.moroz.service;

import com.moroz.exceptions.MovieShowNotFoundException;
import com.moroz.exceptions.OccupiedRowAndPlaceException;
import com.moroz.exceptions.TicketNotFoundException;
import com.moroz.model.TicketDTO;
import com.moroz.parsers.TicketEntityToDTOParser;
import com.moroz.persistence.entities.MovieShowEntity;
import com.moroz.persistence.entities.TicketEntity;
import com.moroz.persistence.repo.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class TicketService {

    private TicketRepository ticketRepository;

    private final MovieShowService movieShowService;

    public List<TicketDTO> getTickets() {
        List<TicketEntity> entities = ticketRepository.findAll();

        List<TicketDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new TicketDTO(x.getMovieShowEntity().getId(), x.getRow(), x.getPlace(),
                x.getTicketStatus().getId(), x.getCreationDate(), x.getModificationDate(), x.getPaymentId())));


        log.info("Found all tickets");

        return dtoList;
    }

    public TicketDTO getTicketById(Long id) {
        TicketEntity entity = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);

        log.info("Found " + entity);

        return TicketEntityToDTOParser.parse(entity);
    }


    public TicketDTO addTicket(Long movieShowId, int row, int place) {
        MovieShowEntity movieShowEntity = movieShowService.getMovieShowRepository().findById(movieShowId)
                .orElseThrow(MovieShowNotFoundException::new);

        TicketEntity ticketEntity = new TicketEntity(movieShowEntity, row, place);

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

    public TicketDTO updateTicket(Long movieShowId, int row, int place, ) {
        MovieShowEntity movieShowEntity = movieShowService.getMovieShowRepository().findById(movieShowId)
                .orElseThrow(MovieShowNotFoundException::new);

        TicketEntity ticketEntity = ticketRepository.findByMovieShowEntityAndRowAndPlace(movieShowEntity, row, place)
                .orElseThrow(TicketNotFoundException::new);

        ticketRepository.insertTicket(ticketEntity.getMovieShowEntity().getId(),
                ticketEntity.getRow(), ticketEntity.getPlace());

        log.info("Saved " + ticketEntity);

        return TicketEntityToDTOParser.parse(ticketEntity);
    }
}
