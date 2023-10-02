package com.useinet;

import com.useinet.model.Movie;
import com.useinet.repository.MovieRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class MovieControllerTest {
    @Autowired
    MovieRepository movieRepository;
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        movieRepository.deleteAll();
        Movie m1 = new Movie(1980, "Friday the 13th", "Associated Film Distribution", "Allan Carr", "yes");
        Movie m2 = new Movie(1981, "Friday the 13th", "Associated Film Distribution", "Allan Carr", "yes");
        Movie m3 = new Movie(1985, "Friday the 13th 2", "Associated Film Distribution", "Allan", "yes");
        Movie m4 = new Movie(1989, "Friday the 13th 2", "Associated Film Distribution", "Allan", "yes");
        movieRepository.saveAll(List.of(m1, m2, m3, m4));
    }

    @Test
    void testFindInterval() {
        String result = "{min=[{producer=Allan Carr, interval=1, previousWin=1980, followingWin=1981}], max=[{producer=Allan, interval=4, previousWin=1985, followingWin=1989}]}";
        given().contentType(ContentType.JSON).when().get("/movies/find/interval").then().statusCode(200).contentType(ContentType.JSON).body(".", hasToString(result));
    }
}