package com.moroz.persistence.repo;

import com.moroz.persistence.entities.TicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketStatusRepository extends JpaRepository<TicketStatusEntity, Long> {
    Optional<TicketStatusEntity> findByName(String name);
}
