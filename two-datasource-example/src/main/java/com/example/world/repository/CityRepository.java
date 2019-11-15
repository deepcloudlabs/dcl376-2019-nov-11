package com.example.world.repository;

import java.util.Collection;

import com.example.repository.GenericRepository;
import com.example.world.entity.City;

public interface CityRepository extends GenericRepository<City, Long> {
	Collection<City> findByCountryCode(String countryCode);
}
