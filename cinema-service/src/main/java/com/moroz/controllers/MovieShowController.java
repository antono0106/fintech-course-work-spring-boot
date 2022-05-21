package com.moroz.controllers;

import com.moroz.model.MovieShowDTO;
import com.moroz.service.MovieShowService;
import lombok.AllArgsConstructor;
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
}
