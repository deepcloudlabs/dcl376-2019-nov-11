package com.example.imdb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("movies")
public class MovieRestController {
	private MovieService movieService;

	public MovieRestController(MovieService movieService) {
		this.movieService = movieService;
	}

	// http://localhost:6001/imdb/api/v1/movies/1	
	@GetMapping("{id}")
	public Movie findById(@PathVariable long id) {
		return movieService.findMovieById(id);
	}
}
