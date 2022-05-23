package com.moroz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {

    private Long id;

    private int amount;

    private String card;

    private Long statusId;

    private Long ticketId;

    public PaymentDTO(Long id, int amount, String card, Long statusId) {
        this.id = id;
        this.amount = amount;
        this.card = card;
        this.statusId = statusId;
    }
}
