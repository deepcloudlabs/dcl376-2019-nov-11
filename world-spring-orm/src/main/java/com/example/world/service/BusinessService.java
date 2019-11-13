package com.example.world.service;

import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;

@Service
public class BusinessService {
	private static final Consumer<Country> printCountry = country -> {
		System.err.println(country);
		System.err.println(country.getCapital());
		country.getCities().forEach(System.err::println);
		country.getLanguages().forEach(System.err::println);
	};

	@Autowired
	private CountryRepository repo;

	@Transactional
	public void haveFun() {
		repo.findById("TUR").ifPresent(printCountry);
		repo.findAll(0, 10).forEach(printCountry);
	}
}
