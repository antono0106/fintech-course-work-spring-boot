package com.moroz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusDTO {

    private Long id;

    private String name;

    public PaymentStatusDTO(String name) {
        this.name = name;
    }
}
