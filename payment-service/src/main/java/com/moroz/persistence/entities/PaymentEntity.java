package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentEntity extends AbstractEntity {

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "card", nullable = false)
    private String card;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private PaymentStatusEntity paymentStatus;
}
