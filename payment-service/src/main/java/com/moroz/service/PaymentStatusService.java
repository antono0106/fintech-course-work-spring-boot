package com.moroz.service;

import com.moroz.persistence.entities.PaymentStatusEntity;
import com.moroz.persistence.repo.PaymentStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentStatusService {

    private PaymentStatusRepository paymentStatusRepository;

    public PaymentStatusEntity getStatusByName(String name) {
        return paymentStatusRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    public List<PaymentStatusEntity> getStatuses() {
        return paymentStatusRepository.findAll();
    }
}
