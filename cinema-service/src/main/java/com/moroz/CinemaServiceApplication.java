package com.moroz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableAsync
public class CinemaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }

}
