package com.moroz.controllers;

import com.moroz.exceptions.UserNotFoundException;
import com.moroz.model.MovieShowDTO;
import com.moroz.service.MovieShowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie-shows")
@AllArgsConstructor
public class MovieShowController {

    private MovieShowService movieShowService;

    @GetMapping
    public List<MovieShowDTO> getMovieShows() {
        return movieShowService.getMovieShows();
    }

    @PostMapping("/create-movie-show")
    public MovieShowDTO createMovieShow(@RequestBody MovieShowDTO movieShowDTO) {
        return movieShowService.createMovieShow(movieShowDTO.getCinemaId(), movieShowDTO.getMovieId(),
                movieShowDTO.getTime(), movieShowDTO.getPrice());
    }

    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteMovieShowById(@PathVariable(value = "id") Long id) {
        movieShowService.deleteMovieShowById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
