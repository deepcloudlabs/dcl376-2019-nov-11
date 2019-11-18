package com.example.imdb.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("/v2/movies")
@Validated
public class MovieRestControllerV2 {
	private MovieService movieService;

	public MovieRestControllerV2(MovieService movieService) {
		this.movieService = movieService;
	}

	// http://localhost:6001/imdb/api/v2/movies/1
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie findById(@PathVariable @Validated @Positive long id) {
		return movieService.findMovieById(id);
	}

	// http://localhost:6001/imdb/api/v2/movies?pageNo=0&pageSize=10
	@GetMapping(params = { "pageNo", "pageSize" })
	public List<Movie> findAll(@RequestParam int pageNo, @RequestParam int pageSize) {
		return movieService.findAllMovies(pageNo, pageSize);
	}
}
