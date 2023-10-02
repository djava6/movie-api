package com.useinet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer dateYear;
    private String studios;
    private String producers;
    private String winner;

    public Movie() {
    }

    public Movie(Integer dateYear, String title, String studios, String producers, String winner) {
        this.dateYear = dateYear;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", dateYear=" + dateYear + ", studios='" + studios + '\'' + ", producers='" + producers + '\'' + ", winner='" + winner + '\'' + '}';
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDateYear() {
        return dateYear;
    }

    public void setDateYear(Integer dateYear) {
        this.dateYear = dateYear;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
