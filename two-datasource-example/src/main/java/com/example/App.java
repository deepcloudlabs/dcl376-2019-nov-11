package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.imdb.config.ImdbAppConfig;
import com.example.imdb.repository.MovieRepository;
import com.example.world.config.WorldAppConfig;
import com.example.world.repository.CountryRepository;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(WorldAppConfig.class,
				ImdbAppConfig.class);
		CountryRepository countryRepostory = container.getBean(CountryRepository.class);
		MovieRepository movieRepository = container.getBean(MovieRepository.class);
		countryRepostory.findAll().forEach(System.out::println);
		movieRepository.findAll().forEach(System.out::println);
		container.close();
	}
}
