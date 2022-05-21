package com.moroz.parsers;

import com.moroz.model.MovieShowDTO;
import com.moroz.persistence.entities.MovieShowEntity;

public class MovieShowEntityToDTOParser {
    public static MovieShowDTO parse(MovieShowEntity entity) {
        return new MovieShowDTO(entity.getId(), entity.getCinemaEntity().getId(),
                entity.getMovieEntity().getId(), entity.getTime(), entity.getPrice());
    }
}
