package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinemaEntity extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rows_amount")
    private int rowsAmount;

    @Column(name = "places_per_row_amount")
    private int placesPerRowAmount;

    public CinemaEntity(String name) {
        this.name = name;
        this.rowsAmount = 15;
        this.placesPerRowAmount = 13;
    }
}
