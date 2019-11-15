package com.example.imdb.service;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

@Service
public class MovieService {
	private static final Supplier<IllegalArgumentException> cannotFindMovie = ()->new IllegalArgumentException("Cannot find movie!");
	private MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	//@Transactional(propagation = Propagation.NEVER)
	public Movie findMovieById(long id) {
		return movieRepository.findById(id)
			   	              .orElseThrow(cannotFindMovie);
	}

}
