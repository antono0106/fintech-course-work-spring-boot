package com.moroz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CinemaDTO {

    private Long id;

    private String name;

    private int rows;

    private int placesPerRowAmount;


    public CinemaDTO(Long id, String name) {
        this.id = id;
        this.name = name;
        this.rows = 15;
        this.placesPerRowAmount = 13;
    }
}
