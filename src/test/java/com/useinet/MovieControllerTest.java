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
    }

    @Test
    void testFindInterval() {
        String result = "{min=[{producer=Joel Silver, interval=1, previousWin=1990, followingWin=1991}], max=[{producer=Matthew Vaughn, interval=13, previousWin=2002, followingWin=2015}]}";
        given().contentType(ContentType.JSON).when().get("/movies/find/interval").then().statusCode(200).contentType(ContentType.JSON).body(".", hasToString(result));
    }
}