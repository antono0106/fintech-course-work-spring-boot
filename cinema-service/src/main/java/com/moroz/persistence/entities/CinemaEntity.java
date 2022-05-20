package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cinema")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CinemaEntity extends AbstractEntity implements com.moroz.persistence.entities.Entity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rows_amount")
    private int rowsAmount;

    @Column(name = "places_per_rows_amount")
    private int placesPerRowAmount;

    public CinemaEntity(String name) {
        this.name = name;
        this.rowsAmount = 15;
        this.placesPerRowAmount = 13;
    }
}
