package com.moroz.service.processor;

import com.moroz.persistence.entities.TicketEntity;
import com.moroz.service.TicketService;
import com.moroz.service.TicketStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class TicketStatusProcessor {

    private TicketService ticketService;
    private TicketStatusService ticketStatusService;

    private final RestTemplate restTemplate = new RestTemplate();

    static {
        log.info("Started " + TicketStatusProcessor.class.getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void updatePayments()  {
        for (TicketEntity entity: ticketService.getProcessingTickets()) {
            String paymentStatusName = restTemplate.getForObject("http://localhost:8081/api/v1/get-payment-status/" + entity.getPaymentId(),
                    String.class);

            entity.setTicketStatusEntity(ticketStatusService.getStatusByName(paymentStatusName));
            entity.setModificationDate(LocalDateTime.now());
            ticketService.getTicketRepository().save(entity);

            log.info("Updated ticket with id " + entity.getId() +" status to " + paymentStatusName);
        }
    }

}
