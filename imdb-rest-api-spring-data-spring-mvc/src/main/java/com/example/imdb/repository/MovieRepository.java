package com.example.imdb.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.imdb.entity.Movie;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Stream<Movie> findAllByYearBetween(int from, int to);

	Optional<Movie> findOneByImdb(String imdb);
}
