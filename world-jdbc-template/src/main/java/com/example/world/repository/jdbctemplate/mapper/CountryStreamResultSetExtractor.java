package com.example.world.repository.jdbctemplate.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.entity.GrossNationalProduct;
import com.example.world.entity.Population;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public enum CountryStreamResultSetExtractor implements ResultSetExtractor<Stream<Country>> {

	INSTANCE;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Stream<Country> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Spliterator<Country> spliterator = Spliterators.spliteratorUnknownSize(new Iterator<Country>() {
			@Override
			public boolean hasNext() {
				try {
					return !rs.isAfterLast();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
				return false;
			}

			@Override
			public Country next() {
				Country country = new Country();
				try {
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
					rs.next();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				return country;
			}
		}, Spliterator.IMMUTABLE);
		return StreamSupport.stream(spliterator, false);
	}
}
