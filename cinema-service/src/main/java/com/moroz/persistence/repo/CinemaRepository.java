package com.moroz.persistence.repo;

import com.moroz.persistence.entities.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
    Optional<CinemaEntity> getCinemaEntityByName(String name);
}
