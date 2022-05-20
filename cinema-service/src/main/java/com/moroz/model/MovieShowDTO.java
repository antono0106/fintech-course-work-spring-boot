package com.moroz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieShowDTO {

    private Long id;

    private Long cinemaId;

    private Long movieId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime time;

    private int price;
}
