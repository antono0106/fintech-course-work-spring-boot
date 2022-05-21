package com.moroz.persistence.repo;

import com.moroz.persistence.entities.MovieShowEntity;
import com.moroz.persistence.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, MovieShowEntity> {
}
