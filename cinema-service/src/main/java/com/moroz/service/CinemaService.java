package com.moroz.service;

import com.moroz.exceptions.CinemaNotFoundException;
import com.moroz.exceptions.MovieNotFoundException;
import com.moroz.model.CinemaDTO;
import com.moroz.parsers.CinemaEntityToDTOParser;
import com.moroz.persistence.entities.CinemaEntity;
import com.moroz.persistence.entities.MovieEntity;
import com.moroz.persistence.repo.CinemaRepository;
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
public class CinemaService {

    private CinemaRepository cinemaRepository;

    protected CinemaRepository getCinemaRepository() {
        return cinemaRepository;
    }

    public List<CinemaDTO> getCinemas() {
        List<CinemaEntity> entities = cinemaRepository.findAll();

        List<CinemaDTO> dtoList = new ArrayList<>();

        entities.forEach(x -> dtoList.add(new CinemaDTO(x.getId(), x.getName(),
                x.getRowsAmount(), x.getPlacesPerRowAmount())));

        log.info("Found all cinemas");

        return dtoList;
    }

    public CinemaDTO getCinemaById(Long id) {
        CinemaEntity entity = cinemaRepository.findById(id)
                .orElseThrow(CinemaNotFoundException::new);

        log.info("Found entity by id " + entity);

        return CinemaEntityToDTOParser.parse(entity);
    }

    public CinemaDTO getCinemaByName(String name) {
        CinemaEntity entity = cinemaRepository.getCinemaEntityByName(name)
                .orElseThrow(CinemaNotFoundException::new);

        log.info("Found entity by id " + entity);

        return CinemaEntityToDTOParser.parse(entity);
    }

    public CinemaDTO createCinema(String name) {
        CinemaEntity entity = new CinemaEntity(name);

        cinemaRepository.save(entity);

        log.info("Saved " + entity);

        return CinemaEntityToDTOParser.parse(entity);
    }

    public CinemaDTO createCinema(String name, int rowAmount, int placesPerRowAmount) {
        CinemaEntity entity = new CinemaEntity(name, rowAmount, placesPerRowAmount);

        cinemaRepository.save(entity);

        log.info("Saved " + entity);

        return CinemaEntityToDTOParser.parse(entity);
    }

    public void deleteCinemaByName(String name) {
        CinemaEntity entity = cinemaRepository.getCinemaEntityByName(name)
                .orElseThrow(CinemaNotFoundException::new);

        cinemaRepository.delete(entity);

        log.info("Deleted entity " + entity);
    }

    public void deleteMovieById(Long id) {
        cinemaRepository.deleteById(id);
        log.info("Deleted entity by id " + id);
    }
}
