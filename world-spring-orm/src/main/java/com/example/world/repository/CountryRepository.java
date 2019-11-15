package com.example.world.repository;

import java.util.List;
import java.util.Set;

import com.example.world.entity.Country;
import com.example.world.entity.CountryCapital;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface CountryRepository extends GenericRepository<Country, String> {
	Set<String> findAllContinents();

	List<Country> findAllByContinent(String continent);
	List<CountryCapital> findAllCapitalsByContinent(String continent);
}
