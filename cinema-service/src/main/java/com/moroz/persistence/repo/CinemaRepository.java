package com.moroz.persistence.repo;

import com.moroz.persistence.entities.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
}
