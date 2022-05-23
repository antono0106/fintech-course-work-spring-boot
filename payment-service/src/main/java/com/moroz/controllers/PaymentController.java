package com.moroz.controllers;

import com.moroz.model.PaymentDTO;
import com.moroz.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
    }

    @PostMapping("/add-payment")
    public PaymentDTO addPayment(@RequestBody PaymentDTO dto) {
        return paymentService.addPayment(dto.getAmount(), dto.getCard(), dto.getTicketId());
    }
}
