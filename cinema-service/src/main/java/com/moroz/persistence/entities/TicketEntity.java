package com.moroz.persistence.entities;

import com.moroz.persistence.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@Data
public class TicketEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long tId;

    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShowEntity movieShowEntity;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "place", nullable = false)
    private int place;

    @Column(name = "status_id", nullable = false)
    private TicketStatus ticketStatus;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @Column(name = "payment_id")
    private Long paymentId;

    public TicketEntity(MovieShowEntity movieShowEntity, int row, int place, TicketStatus ticketStatus, LocalDateTime creationDate, LocalDateTime modificationDate, Long paymentId) {
        this.movieShowEntity = movieShowEntity;
        this.row = row;
        this.place = place;
        this.ticketStatus = ticketStatus;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.paymentId = paymentId;
    }

    public TicketEntity(MovieShowEntity movieShowEntity, int row, int place) {
        this.movieShowEntity = movieShowEntity;
        this.row = row;
        this.place = place;
        this.ticketStatus = TicketStatus.NEW;
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
        this.paymentId = null;
    }
}
