package com.example.world;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.service.BusinessService;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@SuppressWarnings("unused")
public class App3 {
	private static final Consumer<Country> printCountry = country -> {
		System.err.println(country);
		System.err.println(country.getCapital());
		System.err.println(country.getCities().getClass());
		System.err.println(country.getLanguages().getClass());
		country.getCities().forEach(System.err::println);
		country.getLanguages().forEach(System.err::println);
	};

	public static void main(String[] args) {
		try (ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class)) {
			final CountryRepository repo = container.getBean(CountryRepository.class);
			Optional<Country> country = repo.findById("TUR");
			System.out.println(country.isPresent());
			country.ifPresent(printCountry);
		}
	}
}
