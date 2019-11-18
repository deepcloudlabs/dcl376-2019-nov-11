package com.example.imdb.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.imdb.entity.Movie;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	Stream<Movie> findAllByYearBetween(int from,int to);
	Optional<Movie> findOneByImdb(String imdb);
}
