package com.useinet.helper;

import com.useinet.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {
    public static List<Movie> csvToMovies(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)); CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withDelimiter(';').withTrim())) {

            List<Movie> movies = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String str = csvRecord.get(3);
                String[] split = str.split(" and ");
                Movie movie;
                for (String s : split) {
                    String[] split1 = s.split(", ");
                    for (String s1 : split1) {
                        movie = new Movie(Integer.valueOf(csvRecord.get(0)), csvRecord.get(1), csvRecord.get(2), s1, csvRecord.get(4));
                        movies.add(movie);
                    }
                }
            }
            return movies;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
