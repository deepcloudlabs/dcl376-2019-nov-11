package com.example.world.repository;

import java.util.Collection;

import com.example.repository.GenericRepository;
import com.example.world.entity.Country;

public interface CountryRepository extends GenericRepository<Country, String> {
	Collection<Country> getByContinent(String continent);

	Collection<String> getContinents();
}
