package com.moroz.controllers;

import com.moroz.model.PaymentDTO;
import com.moroz.service.PaymentService;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
    }

    @PostMapping("/add-payment")
    public PaymentDTO addPayment(@RequestBody PaymentDTO dto) {
        PaymentDTO innerDto = paymentService.addPayment(dto.getAmount(), dto.getCard(), dto.getTicketId());

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("paymentId", innerDto.getId());
        jsonObject.put("ticketId", dto.getTicketId());

        restTemplate.postForObject("http://localhost:8080/api/v1/tickets/set-payment-id/", jsonObject, String.class);

        return innerDto;
    }

    @GetMapping(path = "/get-payment-status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentById(@PathVariable(value = "id") Long id) {
        return paymentService.getPaymentStatusByPaymentId(id);
    }
}
