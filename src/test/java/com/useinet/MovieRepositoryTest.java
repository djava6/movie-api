package com.useinet;

import com.useinet.model.Movie;
import com.useinet.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = MoviesAPIApp.class)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void findByWinner() {
        List<Movie> result = movieRepository.findByWinner();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}