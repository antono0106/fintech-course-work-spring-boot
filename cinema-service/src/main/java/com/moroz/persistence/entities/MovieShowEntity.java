package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "movie_show")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieShowEntity extends AbstractEntity {

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
