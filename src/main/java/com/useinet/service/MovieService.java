package com.useinet.service;

import com.useinet.helper.CSVHelper;
import com.useinet.model.Interval;
import com.useinet.model.Movie;
import com.useinet.model.Producer;
import com.useinet.repository.MovieRepository;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveAll(InputStream is) {
        List<Movie> movies = CSVHelper.csvToMovies(is);
        movieRepository.saveAll(movies);
    }

    public Producer findByInterval() {
        MultiValuedMap<String, Integer> map = new ArrayListValuedHashMap<>();
        List<Movie> movies = movieRepository.findByWinner();
        for (Movie m : movies) {
            map.put(m.getProducers(), m.getDateYear());
        }
        Set<String> prods = map.keySet();
        List<Interval> intervals = new ArrayList<>();
        for (String producer : prods) {
            List<Integer> years = new ArrayList<>(map.get(producer).stream().sorted().toList());
            for (Iterator<Integer> iterator = years.iterator(); iterator.hasNext() && years.size() > 1; ) {
                int i = years.get(1) - years.get(0);
                Interval interval = new Interval(producer, i, years.get(0), years.get(1));
                intervals.add(interval);
                years.remove(0);
            }
        }
        SortedSet<Integer> its = intervals.stream().map(Interval::getInterval).collect(Collectors.toCollection(TreeSet::new));
        intervals.removeIf(inter -> inter.getInterval() > its.first() && inter.getInterval() < its.last());
        List<Interval> min = new ArrayList<>();
        List<Interval> max = new ArrayList<>();
        for (Interval inter : intervals) {
            if (Objects.equals(inter.getInterval(), its.first())) {
                min.add(inter);
            } else {
                max.add(inter);
            }
        }
        return new Producer(min, max);
    }
}