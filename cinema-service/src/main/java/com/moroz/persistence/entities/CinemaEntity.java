package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
@NoArgsConstructor
@Data
public class CinemaEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long cId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "rows_amount")
    private int rowsAmount;

    @Column(name = "places_per_row_amount")
    private int placesPerRowAmount;

    public CinemaEntity(String name, int rowsAmount, int placesPerRowAmount) {
        this.name = name;
        this.rowsAmount = rowsAmount;
        this.placesPerRowAmount = placesPerRowAmount;
    }

    public CinemaEntity(String name) {
        this.name = name;
        this.rowsAmount = 15;
        this.placesPerRowAmount = 13;
    }
}
