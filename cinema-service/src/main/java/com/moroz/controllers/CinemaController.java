package com.moroz.controllers;

import com.moroz.model.CinemaDTO;
import com.moroz.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinemas")
@AllArgsConstructor
public class CinemaController {

    private CinemaService cinemaService;

    @GetMapping
    public List<CinemaDTO> getCinemas() {
        return cinemaService.getCinemas();
    }

    @PostMapping("/create-cinema-default")
    public CinemaDTO createCinemaDefault(@RequestBody CinemaDTO cinemaDTO) {
        return cinemaService.createCinema(cinemaDTO.getName());
    }

    @PostMapping("/create-cinema")
    public CinemaDTO createCinema(@RequestBody CinemaDTO cinemaDTO) {
        return cinemaService.createCinema(cinemaDTO.getName(), cinemaDTO.getRows(), cinemaDTO.getPlacesPerRowAmount());
    }

    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteCinemaById(@PathVariable(value = "id") Long id) {
        cinemaService.deleteCinemaById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
