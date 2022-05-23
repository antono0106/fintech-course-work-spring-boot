package com.moroz.processor;

import com.moroz.persistence.entities.TicketEntity;
import com.moroz.persistence.entities.TicketStatusEntity;
import com.moroz.service.TicketService;
import com.moroz.service.TicketStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TicketStatusProcessor {

    private TicketService ticketService;

    static {
        log.info("Started " + TicketStatusProcessor.class.getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void updatePayments()  {

    }

}
