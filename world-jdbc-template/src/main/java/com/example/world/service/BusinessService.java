package com.example.world.service;

import static com.example.world.entity.CountryHelper.newCountryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.repository.jdbctemplate.mapper.CountryRowMapper;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Service
public class BusinessService {
	private static final RowMapper<Country> countryRowMapper = CountryRowMapper.INSTANCE;

	private static final String SELECT_COUNTRY_BY_CODE = "select co.code,co.name, co.continent,co.population,"
			+ "co.surfacearea,co.gnp,co.capital,ci.id,ci.name as capitalname,ci.population as capitalpopulation from country co left join city ci"
			+ " on co.capital = ci.id where co.code = :code";

	@Autowired
	private CountryRepository repo;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void fun() {
		System.err.println(jdbcTemplate.getClass());
		System.err.println(repo.getClass());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", "TUR");
		Country country = jdbcTemplate.queryForObject(SELECT_COUNTRY_BY_CODE, parameters, countryRowMapper);
		System.err.println(country);
		try {
			TimeUnit.SECONDS.sleep(3);
			repo.create(newCountryBuilder("APQ").continent("Asia").name("APQ").surfaceArea(123).population(100).gnp(1000)
					.setCapital(100).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
			repo.create(newCountryBuilder("QQQ").continent("Asia").name("APQ").surfaceArea(123).population(100).gnp(1000)
					.setCapital(100).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
