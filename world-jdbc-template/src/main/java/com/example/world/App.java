package com.example.world;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.service.BusinessService;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class App {
	private static final Consumer<Country> printCountry = country -> {
		System.err.println(country);
		System.err.println(country.getCapital());
		country.getCities().forEach(System.err::println);
	};

	public static void main(String[] args) throws Exception {
		try (ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(
				"com.example.world.config")) {
			CountryRepository repo = container.getBean(CountryRepository.class);
//			repo.findById("TUR").ifPresent( printCountry);
//			repo.findById("TUR");
//			repo.findById("TUR");
//			repo.findById("TUR");
//			repo.findById("TUR");
//			repo.findById("TUR");
//			repo.removeById("TUR");
//			repo.findById("TUR");
//			repo.findById("TUR");
//			System.err.println("\n\n\nrepo.findAll(0, 10).forEach(printCountry):");
//			repo.findAll(0, 10).forEach(printCountry);
//			System.err.println("\n\n\nrepo.findAllContinents():");
//			System.err.println(repo.findAllContinents());
//			System.err.println("\n\nrepo.findAllByContinent(\"Antarctica\").forEach(printCountry):");
//			repo.findAllByContinent("Asia").forEach(printCountry);

//			repo.create(newCountryBuilder("APQ").continent("Asia").name("APQ").surfaceArea(123).population(100).gnp(1000).setCapital(100).build());
//			repo.findById("APQ").ifPresent(country -> {
//				country.setSurfaceArea(country.getSurfaceArea()+1);
//				country.setPopulation(new Population(country.getPopulation().getValue()+1));
//				country.setGnp(new GrossNationalProduct(country.getGnp().getValue()+1));
//				repo.update(country);
//			});
//			repo.findById("APQ").ifPresent(country -> {
//				System.err.println("49");
//				country.getCapital().setId(null);
//				repo.update(country);
//			});
			repo.removeById("APQ").ifPresent(printCountry);
			repo.removeById("QQQ").ifPresent(printCountry);
			BusinessService bs = container.getBean(BusinessService.class);
			bs.fun();
			System.err.println(repo.getClass().getSimpleName());
			System.err.println(bs.getClass().getSimpleName());
			TimeUnit.SECONDS.sleep(30);

		}
		System.err.println("DONE.");
	}
}
