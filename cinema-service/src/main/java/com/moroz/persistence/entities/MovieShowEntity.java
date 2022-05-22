package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "movie_show")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieShowEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ms_id")
    private Long msId;

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

    public MovieShowEntity(CinemaEntity cinemaEntity, MovieEntity movieEntity, LocalTime time, int price) {
        this.cinemaEntity = cinemaEntity;
        this.movieEntity = movieEntity;
        this.time = time;
        this.price = price;
    }
}
