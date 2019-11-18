package com.example.world.dao;

import java.util.Collection;
import java.util.Set;

import com.example.world.entity.Country;

public interface CountryDao extends GenericDao<Country, String> {
	Collection<Country> getByContinent(String continent);

	Set<String> getContinents();
}
