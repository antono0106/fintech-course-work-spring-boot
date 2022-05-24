package com.moroz.service;

import com.moroz.exceptions.PaymentNotFoundException;
import com.moroz.model.PaymentDTO;
import com.moroz.parsers.PaymentEntityToDTOParser;
import com.moroz.persistence.entities.PaymentEntity;
import com.moroz.persistence.repo.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PaymentService {

    @Getter
    private PaymentRepository paymentRepository;

    private final PaymentStatusService paymentStatusService;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<PaymentDTO> getPayments() {
        List<PaymentEntity> entities = paymentRepository.findAll();

        List<PaymentDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(PaymentEntityToDTOParser.parse(x)));

        return dtoList;
    }

    public List<PaymentEntity> getNewPayments() {
        return paymentRepository.findAllByPaymentStatus(paymentStatusService.getStatusByName("NEW"));
    }

    public PaymentDTO addPayment(int amount, String card, Long ticketId) {
        PaymentEntity paymentEntity = new PaymentEntity(amount, card, paymentStatusService.getStatusByName("NEW"));

        PaymentEntity newPaymentEntity = paymentRepository.save(paymentEntity);

        return PaymentEntityToDTOParser.parse(newPaymentEntity);
    }

    public String getPaymentStatusByPaymentId(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        return paymentEntity.getPaymentStatus().getName();
    }
}
