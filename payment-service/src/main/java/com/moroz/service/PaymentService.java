package com.moroz.service;

import com.moroz.model.PaymentDTO;
import com.moroz.parsers.PaymentEntityToDTOParser;
import com.moroz.persistence.entities.PaymentEntity;
import com.moroz.persistence.repo.PaymentRepository;
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
public class PaymentService {

    private PaymentRepository paymentRepository;

    public List<PaymentDTO> getPayments() {
        List<PaymentEntity> entities = paymentRepository.findAll();

        List<PaymentDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(PaymentEntityToDTOParser.parse(x)));

        return dtoList;
    }
}
