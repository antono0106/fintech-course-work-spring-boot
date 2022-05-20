package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketStatusEntity extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
}
