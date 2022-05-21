package com.moroz.parsers;

import com.moroz.model.MovieDTO;
import com.moroz.persistence.entities.MovieEntity;

public class MovieEntityToDTOParser {
    public static MovieDTO parse(MovieEntity entity) {
        return new MovieDTO(entity.getId(), entity.getName());
    }
}
