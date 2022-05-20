package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@javax.persistence.Entity
@Table(name = "movie_show")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieShowEntity extends AbstractEntity implements com.moroz.persistence.entities.Entity {
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinemaEntity;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "price", nullable = false)
    private int price;
}
