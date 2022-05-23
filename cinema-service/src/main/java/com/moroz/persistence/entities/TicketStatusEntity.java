package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketStatusEntity extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
