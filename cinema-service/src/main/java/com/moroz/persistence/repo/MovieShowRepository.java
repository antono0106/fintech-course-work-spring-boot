package com.moroz.persistence.repo;

import com.moroz.persistence.entities.MovieShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<MovieShowEntity, Long> {
}
