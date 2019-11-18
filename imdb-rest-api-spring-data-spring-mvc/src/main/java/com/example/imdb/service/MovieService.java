package com.example.imdb.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepo;

	public Movie findMovieById(Integer id) {
		Supplier<RuntimeException> notFoundSupplier = () -> new IllegalArgumentException("Cannot find movie");
		return movieRepo.findById(id).orElseThrow(notFoundSupplier);
	}

	public List<Movie> findMovies(int pageNo, int pageSize) {
		return movieRepo.findAll(PageRequest.of(pageNo, pageSize)).getContent();
	}

	@Transactional
	public Movie newMovie(Movie movie) {
		return movieRepo.save(movie);
	}

	@Transactional
	public Movie updateMovie(Movie movie) {
		Integer id = movie.getMovieId();
		Optional<Movie> optionalMovie = movieRepo.findById(id);
		optionalMovie.ifPresent(managed -> {
			managed.setTitle(movie.getTitle());
			managed.setYear(movie.getYear());
			managed.setImdb(movie.getImdb());
		});
		return optionalMovie.orElseThrow(() -> new IllegalArgumentException("Cannot find movie to update!"));
	}

	@Transactional
	public Movie removeMovieById(@Min(1) Integer id) {
		Optional<Movie> optionalMovie = movieRepo.findById(id);
		optionalMovie.ifPresent(managed -> {
			movieRepo.delete(managed);
		});
		return optionalMovie.orElseThrow(() -> new IllegalArgumentException("Cannot find movie to delete!"));
	}

}
