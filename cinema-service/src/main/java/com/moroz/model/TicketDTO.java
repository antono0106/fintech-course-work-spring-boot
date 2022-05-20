package com.moroz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDTO {

    private Long movieShowId;

    private int row;

    private int place;

    private Long ticketStatusId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modificationDate;

    private Long paymentId;

    public TicketDTO(Long movieShowId, int row, int place) {
        this.movieShowId = movieShowId;
        this.row = row;
        this.place = place;
        this.ticketStatusId = 1L;
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
        this.paymentId = null;
    }
}
