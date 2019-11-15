package com.example.imdb.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

	public List<Movie> findAllMovies(int pageNo, int pageSize) {
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		return movieRepository.findAll(pageRequest).getContent();
	}

}
