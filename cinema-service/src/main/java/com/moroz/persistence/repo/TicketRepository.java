package com.moroz.persistence.repo;

import com.moroz.persistence.entities.MovieShowEntity;
import com.moroz.persistence.entities.TicketEntity;
import com.moroz.persistence.entities.TicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Modifying
    @Query(value = "INSERT INTO ticket (movie_show_id, row, place) SELECT :movie_show_id," +
            " :row, :place WHERE NOT EXISTS(SELECT * FROM ticket WHERE movie_show_id = :movie_show_id AND " +
            "row = :row AND place = :place)",
            nativeQuery = true)
    void insertTicket(@Param("movie_show_id") Long movieShowId, @Param("row") int row, @Param("place") int place);

    Optional<TicketEntity> findByMovieShowEntityAndRowAndPlace(MovieShowEntity movieShowEntity, int row, int place);

    List<TicketEntity> findAllByTicketStatus(TicketStatusEntity ticketStatusEntity);
}
