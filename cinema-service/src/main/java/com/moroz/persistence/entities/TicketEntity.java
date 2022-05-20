package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketEntity {
    @Id
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
}
