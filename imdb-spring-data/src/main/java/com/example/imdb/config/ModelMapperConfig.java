package com.example.imdb.config;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.imdb.dto.MovieDTO;
import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		Converter<Movie, MovieDTO> movieToMovieDTOConverter =
				context -> {
					Movie movie = context.getSource();
					MovieDTO movieDTO= new MovieDTO();
					movieDTO.setId(movie.getId());
					movieDTO.setTitle(movie.getTitle());
					movieDTO.setImdb(movie.getImdb());
					movieDTO.setGenres(movie.getGenres()
							   .stream()
							   .map(Genre::getDescription)
							   .collect(Collectors.toList()));
					return movieDTO;
				};
		modelMapper.addConverter(movieToMovieDTOConverter,Movie.class,MovieDTO.class);
		return modelMapper;
	}
}
