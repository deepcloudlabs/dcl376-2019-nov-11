package com.example.world.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.entity.CountryStatistics;
import com.example.world.repository.CountryRepository;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Service
public class BusinessService {
	private static final Consumer<Country> printCountry = country -> {
		System.err.println(country);
		City capital = country.getCapital();
		if (Objects.nonNull(capital)) {
			System.err.println(capital);
		}
		country.getCities().forEach(System.err::println);
		country.getLanguages().forEach(System.err::println);
	};

	@Autowired
	private CountryRepository repo;

	@Transactional
	public void haveGun() {
		repo.removeById("AAA").ifPresent(printCountry);
	}

	@SuppressWarnings("unused")
	@Transactional
	public void haveFun() {
		// repo.findById("TUR").ifPresent(printCountry);
		repo.findAll(0, 1).stream().filter(country -> Objects.nonNull(country.getStatistics().getGnp()))
				.forEach(country -> {
					CountryStatistics stat = country.getStatistics();
					System.err.println(country.getClass());
//			stat.setGnp(stat.getGnp() + 1);
//			country.getCities().forEach(city->city.setPopulation(city.getPopulation()+1));
				});
//		try {
//			TimeUnit.SECONDS.sleep(10);
//			System.err.println(new Date());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	@Transactional
	public void haveSun() {
		repo.findAllStream(0, 100).forEach(printCountry);
	}

	public void findCapitals() {
		repo.findAllCapitalsByContinent("Asia").forEach(System.out::println);
	}

	public void findNativeCapitals() {
		repo.findAllNativeCapitalsByContinent("Asia").forEach(System.out::println);
	}

	@Transactional
	public void haveRun() {
		Country country = new Country();
		country.setCode("BBB");
		country.setName("AAAAAA");
		country.setContinent("Asia");
		CountryStatistics statistics = new CountryStatistics();
		statistics.setPopulation(1111);
		statistics.setSurfaceArea(2111);
		statistics.setGnp(100011D);
		country.setStatistics(statistics);
		City capital = new City();
		capital.setCountry(country);
		capital.setName("CAPAAAA");
		capital.setPopulation(11111);
//		country.setCapital(capital);
		List<City> cities = new ArrayList<>();
		cities.add(capital);
		country.setCities(cities);
		repo.create(country);
	}
}
