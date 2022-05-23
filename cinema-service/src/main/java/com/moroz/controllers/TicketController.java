package com.moroz.controllers;

import com.moroz.exceptions.UserNotFoundException;
import com.moroz.model.TicketDTO;
import com.moroz.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@AllArgsConstructor
public class TicketController {

    private TicketService ticketService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping("/create-ticket")
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO dto = ticketService.addTicket(ticketDTO.getMovieShowId(), ticketDTO.getRow(), ticketDTO.getPlace());
        restTemplate.postForObject("http://localhost:8080/api/v1/");
        return dto;
    }

    @PostMapping(path ="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteTicket(@PathVariable(name = "id") Long id) {
        ticketService.deleteTicketById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
