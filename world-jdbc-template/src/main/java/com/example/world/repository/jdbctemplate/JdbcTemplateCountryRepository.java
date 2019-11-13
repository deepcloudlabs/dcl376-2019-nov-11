package com.example.world.repository.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.repository.jdbctemplate.mapper.CityRowMapper;
import com.example.world.repository.jdbctemplate.mapper.CountryRowMapper;
import com.example.world.repository.jdbctemplate.mapper.CountryStreamResultSetExtractor;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Repository
public class JdbcTemplateCountryRepository implements CountryRepository {
	private static final String SELECT_COUNTRY_BY_CODE = "select co.code,co.name, co.continent,co.population,"
			+ "co.surfacearea,co.gnp,co.capital,ci.id,ci.name as capitalname,ci.population as capitalpopulation from country co left join city ci"
			+ " on co.capital = ci.id where co.code = :code";

	private static final String SELECT_COUNTRY_BY_CONTINENT = "select co.code,co.name, co.continent,co.population,"
			+ "co.surfacearea,co.gnp,co.capital,ci.id,ci.name as capitalname,ci.population as capitalpopulation from country co, city ci"
			+ " where co.continent = :continent and co.capital = ci.id";

	private static final String SELECT_COUNTRIES = "select co.code,co.name, co.continent,co.population,"
			+ "co.surfacearea,co.gnp,co.capital,ci.id,ci.name as capitalname,ci.population as capitalpopulation from country co, city ci"
			+ " where co.capital=ci.id limit :offset,:size";

	private static final String SELECT_CITIES_BY_COUNTRYCODE = "select id,name,population"
			+ " from city where countrycode = :code";

	private static final String SELECT_DISTINCT_CONTINENTS = "select distinct continent from country";

	private static final String INSERT_INTO_COUNTRY = "insert into country(code,continent,name,population,surfacearea,gnp,capital)"
			+ " VALUES (:code,:continent,:name,:population,:surfacearea,:gnp,:capital)";

	private static final String UPDATE_COUNTRY = "update country set population=:population,"
			+ " surfacearea=:surfacearea, gnp=:gnp, capital=:capital" + " where code=:code";

	private static final String DELETE_COUNTRY_BY_CODE = "delete from country where code=:code";

	private static final RowMapper<Country> countryRowMapper = CountryRowMapper.INSTANCE;
	private static final ResultSetExtractor<Stream<Country>> countryStreamResultSetExtractor = CountryStreamResultSetExtractor.INSTANCE;
	private static final RowMapper<City> cityRowMapper = CityRowMapper.INSTANCE;

	private NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcTemplateCountryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<Country> findById(String code) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", code);
		Country country = jdbcTemplate.queryForObject(SELECT_COUNTRY_BY_CODE, parameters, countryRowMapper);
		List<City> cities = jdbcTemplate.query(SELECT_CITIES_BY_COUNTRYCODE, parameters, cityRowMapper);
		country.setCities(new HashSet<>(cities));
		return Optional.ofNullable(country);
	}

	@Override
	public List<Country> findAll(int pageNo, int pageSize) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("offset", pageNo * pageSize);
		parameters.put("size", pageSize);
		final List<Country> countries = jdbcTemplate.query(SELECT_COUNTRIES, parameters, countryRowMapper);
		countries.forEach(country -> {
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", country.getCode());
			List<City> cities = jdbcTemplate.query(SELECT_CITIES_BY_COUNTRYCODE, params, cityRowMapper);
			country.setCities(new HashSet<>(cities));
		});
		return countries;
	}

	@Override
	@Transactional
	public Stream<Country> findAllStream(int pageNo, int pageSize) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("offset", pageNo * pageSize);
		parameters.put("size", pageSize);
		final Stream<Country> countries = jdbcTemplate.query(SELECT_COUNTRIES, parameters,
				countryStreamResultSetExtractor);
		countries.forEach(System.err::println);
		return countries;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void create(Country country) {
		try {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", country.getCode());
		parameters.put("name", country.getName());
		parameters.put("continent", country.getContinent());
		parameters.put("population", country.getPopulation().getValue());
		parameters.put("surfacearea", country.getSurfaceArea());
		parameters.put("gnp", country.getGnp().getValue());
		parameters.put("capital", country.getCapital().getId());
		jdbcTemplate.update(INSERT_INTO_COUNTRY, parameters);
		throw new IllegalArgumentException("Ooopps");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT)
	public void update(Country country) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", country.getCode());
		parameters.put("population", country.getPopulation().getValue());
		parameters.put("surfacearea", country.getSurfaceArea());
		parameters.put("gnp", country.getGnp().getValue());
		parameters.put("capital", country.getCapital().getId());
		jdbcTemplate.update(UPDATE_COUNTRY, parameters);
	}

	@Override
	@CacheEvict(value = "countries", key = "#code")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public Optional<Country> removeById(String code) {
		try {
			final Optional<Country> country = findById(code);
			if (country.isPresent()) {
				final Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("code", code);
				final int numOfRemoved = jdbcTemplate.update(DELETE_COUNTRY_BY_CODE, parameters);
				if (numOfRemoved > 0) {
					return country;
				}
			}
		} catch (Exception e) {
			System.err.println("Cannot find the entity to remove: " + e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	@CacheEvict(value = "countries", key = "#country.code")
	@Transactional
	public void remove(Country country) {
		removeById(country.getCode());
	}

	@Override
	public Set<String> findAllContinents() {
		List<String> continents = jdbcTemplate.query(SELECT_DISTINCT_CONTINENTS, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("continent");
			}
		});
		return new HashSet<>(continents);
	}

	@Override
	public List<Country> findAllByContinent(String continent) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("continent", continent);
		List<Country> countries = jdbcTemplate.query(SELECT_COUNTRY_BY_CONTINENT, parameters, countryRowMapper);
		countries.forEach(country -> {
			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", country.getCode());
			List<City> cities = jdbcTemplate.query(SELECT_CITIES_BY_COUNTRYCODE, params, cityRowMapper);
			country.setCities(new HashSet<>(cities));
		});
		return countries;
	}

}
