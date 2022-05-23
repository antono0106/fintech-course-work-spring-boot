package com.moroz.service.processor;

import com.moroz.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
