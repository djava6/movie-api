package com.useinet.repository;

import com.useinet.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.winner='yes' ORDER BY m.producers")
    List<Movie> findByWinner();
}