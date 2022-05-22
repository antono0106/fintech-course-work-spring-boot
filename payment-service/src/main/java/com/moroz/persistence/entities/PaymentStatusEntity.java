package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentStatusEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
