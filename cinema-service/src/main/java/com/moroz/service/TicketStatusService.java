package com.moroz.service;

import com.moroz.persistence.entities.TicketStatusEntity;
import com.moroz.persistence.repo.TicketStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TicketStatusService {

    private TicketStatusRepository ticketStatusRepository;

    public List<TicketStatusEntity> getStatuses() {
        return ticketStatusRepository.findAll();
    }

    public TicketStatusEntity getStatusByName(String name) {
        return ticketStatusRepository.findByName(name).orElseThrow(RuntimeException::new);
    }
}
