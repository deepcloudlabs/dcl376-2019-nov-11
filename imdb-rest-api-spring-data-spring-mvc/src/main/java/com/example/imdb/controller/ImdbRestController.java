package com.example.imdb.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.dto.ErrorMessage;
import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController // JAX-RS: @Path("movies")
// http(s)://localhost:8001/imdb/api/v1/movies
@RequestMapping("movies")
@RequestScope
@Validated
@CrossOrigin
@Api(value = "movies", tags = "Endpoint for Imdb Movies")
public class ImdbRestController {
	@Autowired
	private MovieService movieSrv;

	// http://localhost:8001/imdb/api/v1/movies/108
	// http://localhost:8001/imdb/api/v1/movies/549
	@GetMapping("{id}")
	@ApiOperation(value = "Returns a customer details.", notes = "Returns customer details for given identity.", response = Movie.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of the movie detail.", response = Movie.class),
			@ApiResponse(code = 404, message = "Movie with the given id does not exist!", response = ErrorMessage.class) })
	public Movie findMovieById(@PathVariable @Validated @Min(1) Integer id) {
		return movieSrv.findMovieById(id);
	}

	@GetMapping
	@ApiOperation(value = "Returns movie details with pagination.", notes = "Returns a complete list of movie details with pagination.", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of movie detail.", response = Movie.class) })
	public List<Movie> findMovies( 
			@ApiParam(name = "pageNo", value = "page number", defaultValue = "1") 
			@RequestParam int pageNo, 
			@ApiParam(name = "pageSize", value = "page size", defaultValue = "25")
	        @RequestParam int pageSize) {
		return movieSrv.findMovies(pageNo, pageSize);
	}

	@PostMapping
	@ApiOperation(value = "Creates a movie.", response = Movie.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful creation of movie.", response = Movie.class),
			@ApiResponse(code = 404, message = "Movie with the given id already exists!", response = ErrorMessage.class) })
	public ResponseEntity<Movie> addMovie(@RequestBody @Validated Movie movie) {
		return ResponseEntity.ok(movieSrv.newMovie(movie));
	}

	@PutMapping
	@ApiOperation(value = "Updates a movie.", response = Movie.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful movie update.", response = Movie.class),
			@ApiResponse(code = 404, message = "Movie with the given id does not exist!", response = ErrorMessage.class) })
	public Movie updateMovie(@RequestBody @Validated Movie movie) {
		return movieSrv.updateMovie(movie);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Deletes a movie.", response = Movie.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful removal of movie.", response = Movie.class),
			@ApiResponse(code = 404, message = "Movie with the given id does not exist!", response = ErrorMessage.class) })
	public Movie removeMovieById(@PathVariable @Validated @Min(1) Integer id) {
		return movieSrv.removeMovieById(id);
	}
}
