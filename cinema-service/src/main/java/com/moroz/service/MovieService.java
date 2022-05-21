package com.moroz.service;

import com.moroz.exceptions.MovieNotFoundException;
import com.moroz.model.MovieDTO;
import com.moroz.parsers.MovieEntityToDTOParser;
import com.moroz.persistence.entities.MovieEntity;
import com.moroz.persistence.repo.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    protected MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public List<MovieDTO> getMovies() {
        List<MovieEntity> entities = movieRepository.findAll();

        List<MovieDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new MovieDTO(x.getId(), x.getName())));

        log.info("Found all movies");

        return dtoList;
    }

    public MovieDTO getMovieByName(String name) {
        MovieEntity entity = movieRepository.getMovieEntityByName(name)
                .orElseThrow(MovieNotFoundException::new);

        log.info("Found entity by name " + entity);

        return MovieEntityToDTOParser.parse(entity);
    }

    public MovieDTO getMovieById(Long id) {
        MovieEntity entity = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        log.info("Found entity by id " + entity);

        return MovieEntityToDTOParser.parse(entity);
    }

    public MovieDTO createMovie(String name) {
        MovieEntity entity = new MovieEntity(name);

        movieRepository.save(entity);

        log.info("Saved " + entity);

        return MovieEntityToDTOParser.parse(entity);
    }

    public void deleteMovieByName(String name) {
        MovieEntity entity = movieRepository.getMovieEntityByName(name)
                .orElseThrow(MovieNotFoundException::new);

        movieRepository.delete(entity);
        log.info("Deleted entity " + entity);
    }

    public void deleteMovieById(Long id) {
        MovieEntity entity = movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        movieRepository.delete(entity);
        log.info("Deleted entity " + entity);
    }
}
