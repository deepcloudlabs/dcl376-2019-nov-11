package com.example.world.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;
import com.example.world.exception.EntityNotFoundException;

@Repository
public class JdbcCountryDao implements CountryDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcCountryDao.class);

	// COLUMN NAMES
	private static final String COLUMN_SURFACE_AREA = "surfaceArea";
	private static final String COLUMN_POPULATION = "population";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_CONTINENT = "continent";
	private static final String COLUMN_CODE = "code";

	// SQL STATEMENTS
	private static final String SELECT_COUNTRY_BY_CODE = "select * from country where code=?";
	private static final String SELECT_COUNTRIES_BY_CONTINENT = "select * from country where continent=?";
	private static final String SELECT_CONTINENTS = "select distinct continent from country";
	private static final String SELECT_COUNTRIES = "select * from country limit ?,?";
	private static final String DELETE_COUNTRY_BY_CODE = "delete from country where code=?";
	private static final String ADD_COUNTRY = "insert into country(code,name,continent,population,surfaceArea) values(?,?,?,?,?)";
	private static final String UPDATE_COUNTRY = "update country set population=? , surfaceArea=? where code=?";
	@Autowired
	private DataSource ds;

	@Override
	public Optional<Country> findOne(String code) {
		try (Connection connection = ds.getConnection();) {
			PreparedStatement st = connection.prepareStatement(SELECT_COUNTRY_BY_CODE);
			st.setString(1, code);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Country country = new Country();
				country.setCode(code);
				country.setName(rs.getString(COLUMN_NAME));
				country.setContinent(rs.getString(COLUMN_CONTINENT));
				country.setPopulation(rs.getInt(COLUMN_POPULATION));
				country.setSurfaceArea(rs.getDouble(COLUMN_SURFACE_AREA));
				return Optional.of(country);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public Collection<Country> findAll(int pageNo, int pageSize) {
		List<Country> countries = new ArrayList<>();
		try (Connection connection = ds.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_COUNTRIES);
			statement.setInt(1, (pageNo - 1) * pageSize);
			statement.setInt(2, pageSize);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				countries.add(extractCountryFromResultSet(rs));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return countries;
	}

	public Country add(Country country) {
		try (Connection conn = ds.getConnection();) {
			PreparedStatement st = conn.prepareStatement(ADD_COUNTRY);
			st.setString(1, country.getCode());
			st.setString(2, country.getName());
			st.setString(3, country.getContinent());
			st.setLong(4, country.getPopulation());
			st.setDouble(5, country.getSurfaceArea());
			int rowsAffected = st.executeUpdate();
			LOGGER.info(String.format("%d rows affected.", rowsAffected));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	public Country update(Country country) {
		String code = country.getCode();
		Optional<Country> existing = findOne(code);
		if (!existing.isPresent())
			throw new EntityNotFoundException("Country does not exist");
		PreparedStatement statement;
		try (Connection connection = ds.getConnection()) {
			statement = connection.prepareStatement(UPDATE_COUNTRY);
			statement.setLong(1, country.getPopulation());
			statement.setDouble(2, country.getSurfaceArea());
			statement.setString(3, country.getCode());
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return country;
	}

	public Optional<Country> remove(String code) {
		Optional<Country> country = findOne(code);
		country.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
		try (Connection connection = ds.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRY_BY_CODE);
			statement.setString(1, code);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return country;
	}

	public Collection<Country> getByContinent(String continent) {
		List<Country> countries = new ArrayList<>();
		try (Connection connection = ds.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_COUNTRIES_BY_CONTINENT);
			statement.setString(1, continent);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				countries.add(extractCountryFromResultSet(rs));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return countries;
	}

	@Override
	public Set<String> getContinents() {
		Set<String> continents = new HashSet<>();
		try (Connection connection = ds.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SELECT_CONTINENTS);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				continents.add(rs.getString(1));
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return continents;
	}

	private Country extractCountryFromResultSet(ResultSet rs) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString(COLUMN_CODE));
		country.setContinent(rs.getString(COLUMN_CONTINENT));
		country.setName(rs.getString(COLUMN_NAME));
		country.setPopulation(rs.getInt(COLUMN_POPULATION));
		country.setSurfaceArea(rs.getDouble(COLUMN_SURFACE_AREA));
		return country;
	}
}
