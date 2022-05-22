package com.moroz.controllers;

import com.moroz.model.PaymentDTO;
import com.moroz.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
    }
}
