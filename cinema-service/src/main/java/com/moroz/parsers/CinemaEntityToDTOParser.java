package com.moroz.parsers;

import com.moroz.model.CinemaDTO;
import com.moroz.persistence.entities.CinemaEntity;

public class CinemaEntityToDTOParser {
    public static CinemaDTO parse(CinemaEntity entity) {
        return new CinemaDTO(entity.getId(), entity.getName(),
                entity.getRowsAmount(), entity.getPlacesPerRowAmount());
    }
}
