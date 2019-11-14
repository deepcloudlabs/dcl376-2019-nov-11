package com.example.world.service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.City;
import com.example.world.entity.Country;
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
	
	@Transactional
	public void haveFun() {
		// repo.findById("TUR").ifPresent(printCountry);
		repo.findAll(0, 200).stream().filter(country -> Objects.nonNull(country.getGnp())).forEach(country -> {
			country.setGnp(country.getGnp() + 1);
			repo.update(country);
		});
		try {
			TimeUnit.SECONDS.sleep(10);
			System.err.println(new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		Country country = new Country();
//		country.setCode("AAA");
//		country.setName("AAAAAA");
//		country.setContinent("Asia");
//		country.setPopulation(1111);
//		country.setSurfaceArea(2111);
//		country.setGnp(100011);
//		repo.update(country);
	}

	@Transactional
	public void haveSun() {
		repo.findAllStream(0, 100).forEach(printCountry);		
	}
}
