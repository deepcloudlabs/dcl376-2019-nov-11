package com.example.world.repository.jdbctemplate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.entity.GrossNationalProduct;
import com.example.world.entity.Population;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public enum CountryRowMapper implements RowMapper<Country> {

	INSTANCE;

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString("code"));
		City capital = new City();
		capital.setId(rs.getInt("capital"));
		capital.setName(rs.getString("capitalname"));
		capital.setPopulation(new Population(rs.getLong("capitalpopulation")));
		country.setCapital(capital);
		country.setContinent(rs.getString("continent"));
		country.setGnp(new GrossNationalProduct(rs.getDouble("gnp")));
		country.setName(rs.getString("name"));
		country.setPopulation(new Population(rs.getLong("population")));
		country.setSurfaceArea(rs.getDouble("surfacearea"));
		return country;
	}
}
