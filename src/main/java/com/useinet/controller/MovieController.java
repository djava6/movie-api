package com.useinet.controller;

import com.useinet.model.Producer;
import com.useinet.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/find/interval")
    public Producer findByInterval() {
        return movieService.findByInterval();
    }
}
