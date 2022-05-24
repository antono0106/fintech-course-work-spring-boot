package com.moroz.controllers;

import com.moroz.exceptions.MovieNotFoundException;
import com.moroz.model.MovieDTO;
import com.moroz.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/add-movie")
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.createMovie(movieDTO.getName());
    }


    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteMovieById(@PathVariable(value = "id") Long id) {
        movieService.deleteMovieById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
