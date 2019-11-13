package com.example.world.repository.jdbctemplate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.world.entity.City;
import com.example.world.entity.Population;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public enum CityRowMapper implements RowMapper<City> {
	INSTANCE;
	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City city = new City();
		city.setId(rs.getInt("id"));
		city.setName(rs.getString("name"));
		city.setPopulation(new Population(rs.getLong("population")));
		return city;
	}
}
