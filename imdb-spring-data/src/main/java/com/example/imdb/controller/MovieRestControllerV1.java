package com.example.imdb.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.dto.MovieDTO;
import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestScope
@CrossOrigin
@RequestMapping("/v1/movies")
public class MovieRestControllerV1 {
	private MovieService movieService;
	private ModelMapper modelMapper;

	public MovieRestControllerV1(MovieService movieService, ModelMapper modelMapper) {
		this.movieService = movieService;
		this.modelMapper = modelMapper;
	}

	// http://localhost:6001/imdb/api/v1/movies/1
	@GetMapping("{id}")
	public MovieDTO findById(@PathVariable long id) {
		Movie movie = movieService.findMovieById(id);
		return modelMapper.map(movie, MovieDTO.class);
	}

	// http://localhost:6001/imdb/api/v1/movies?pageNo=0&pageSize=10
	@GetMapping(params = { "pageNo", "pageSize" })
	public List<MovieDTO> findAll(@RequestParam int pageNo, @RequestParam int pageSize) {
		List<Movie> movies = movieService.findAllMovies(pageNo, pageSize);
		Type listType = new TypeToken<List<MovieDTO>>() {}.getType();
		return modelMapper.map(movies, listType);
	}
}
