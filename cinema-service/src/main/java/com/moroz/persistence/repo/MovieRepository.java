package com.moroz.persistence.repo;

import com.moroz.persistence.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Optional<MovieEntity> getMovieEntityByName(String name);
}
