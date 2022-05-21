package com.moroz.service;

import com.moroz.exceptions.MovieShowNotFoundException;
import com.moroz.model.CinemaDTO;
import com.moroz.model.MovieShowDTO;
import com.moroz.parsers.MovieShowEntityToDTOParser;
import com.moroz.persistence.entities.CinemaEntity;
import com.moroz.persistence.entities.MovieShowEntity;
import com.moroz.persistence.repo.MovieShowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class MovieShowService {

    private MovieShowRepository movieShowRepository;

    private final MovieService movieService;
    private final CinemaService cinemaService;

    public List<MovieShowDTO> getMovieShows() {
        List<MovieShowEntity> entities = movieShowRepository.findAll();

        List<MovieShowDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new MovieShowDTO(x.getId(), x.getCinemaEntity().getId(),
                x.getMovieEntity().getId(), x.getTime(), x.getPrice())));

        log.info("Found all movie shows");

        return dtoList;
    }

    public MovieShowDTO getMovieShowById(Long id) {
        MovieShowEntity entity = movieShowRepository.findById(id)
                .orElseThrow(MovieShowNotFoundException::new);

        log.info("Found " + entity);

        return MovieShowEntityToDTOParser.parse(entity);
    }


}
