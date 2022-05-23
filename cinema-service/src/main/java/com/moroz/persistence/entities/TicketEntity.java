package com.moroz.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShowEntity movieShowEntity;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "place", nullable = false)
    private int place;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private TicketStatusEntity ticketStatusEntity;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @Column(name = "payment_id")
    private Long paymentId;

    public TicketEntity(MovieShowEntity movieShowEntity, int row, int place, TicketStatusEntity ticketStatusEntity) {
        this.movieShowEntity = movieShowEntity;
        this.row = row;
        this.place = place;
        this.ticketStatusEntity = ticketStatusEntity;
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
        this.paymentId = null;
    }
}
