package com.moroz.parsers;

import com.moroz.model.TicketDTO;
import com.moroz.persistence.entities.TicketEntity;

public class TicketEntityToDTOParser {
    public static TicketDTO parse(TicketEntity entity) {
        return new TicketDTO(entity.getMovieShowEntity().getId(), entity.getRow(), entity.getPlace(),
                entity.getTicketStatus().getId(), entity.getCreationDate(),
                entity.getModificationDate(), entity.getPaymentId());
    }
}
