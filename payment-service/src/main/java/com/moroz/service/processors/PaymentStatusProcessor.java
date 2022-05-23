package com.moroz.service.processors;

import com.moroz.persistence.entities.PaymentEntity;
import com.moroz.service.PaymentService;
import com.moroz.service.PaymentStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentStatusProcessor {

    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;

    static {
        log.info("Started " + PaymentStatusProcessor.class.getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void updatePayments() {
        for (PaymentEntity entity : paymentService.getNewPayments()) {
            entity.setPaymentStatus(paymentStatusService.getStatuses().get((Math.random() <= 0.5) ? 2 : 3));
            paymentService.getPaymentRepository().save(entity);
        }
    }
}
