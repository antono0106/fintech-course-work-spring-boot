package com.moroz.controllers;

import com.moroz.model.TicketDTO;
import com.moroz.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketRepository {

    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping("/create-ticket")
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.addTicket(ticketDTO.getMovieShowId(), ticketDTO.getRow(), ticketDTO.getPlace());
    }

    /*@PostMapping
    public TicketDTO updateTicket()*/
}
