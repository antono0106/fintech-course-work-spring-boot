package com.moroz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moroz.model.MovieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {

    /*@Autowired
    private MockMvc mvc;*/

    @MockBean
    private MovieController movieController; // mock the repository

    /*@Autowired
    private ObjectMapper objectMapper;*/

    @Test
    void getMovies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movieDTOS.add(new MovieDTO(23L, "Fighting club"));
        Assertions.assertEquals(movieDTOS, movieController.getMovies());
    }

    @Test
    void createMovie() {
    }

    @Test
    void deleteMovieById() {
    }
}