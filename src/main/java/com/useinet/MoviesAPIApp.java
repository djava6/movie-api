package com.useinet;

import com.useinet.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MoviesAPIApp {
    final MovieService movieService;

    public MoviesAPIApp(MovieService movieService) {
        this.movieService = movieService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoviesAPIApp.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner movieCommandLineRunner() {
        return args -> {
            System.out.println("Importing CSV data to the database...");
            movieService.saveAll(getClass().getClassLoader().getResourceAsStream("movielist.csv"));
        };
    }
}