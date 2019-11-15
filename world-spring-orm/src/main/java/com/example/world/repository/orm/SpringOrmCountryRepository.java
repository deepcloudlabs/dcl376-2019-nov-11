package com.example.world.repository.orm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
import com.example.world.entity.CountryCapital;
import com.example.world.entity.CountryStatistics;
import com.example.world.repository.CountryRepository;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Repository
public class SpringOrmCountryRepository implements CountryRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Country> findById(String code) {
		Map<String,Object> params = new HashMap<>();
		EntityGraph<?> eg = em.getEntityGraph("graph.Country.cities");
		params.put("javax.persistence.fetchgraph",eg);
		Country country = em.find(Country.class, code,params);
		return Optional.ofNullable(country);
	}

	@Override
	public List<Country> findAll(int pageNo, int pageSize) {
		EntityGraph<?> eg = em.getEntityGraph("graph.Country.cities");
		return em.createNamedQuery("Country.findAll", Country.class)
				.setFirstResult(pageNo * pageSize)
				.setMaxResults(pageSize)
				.setHint("javax.persistence.fetchgraph", eg)
				.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Stream<Country> findAllStream(int pageNo, int pageSize) {
		return em.createNamedQuery("Country.findAll", Country.class).setFirstResult(pageNo * pageSize)
				.setMaxResults(pageSize).getResultStream();
	}

	@Override
	@Transactional
	public void create(Country country) {
		em.persist(country);
	}

	@Override
	@Transactional
	public void update(Country country) {
		String code = country.getCode();
		Country managedCountry = em.find(Country.class, code);
		if (Objects.nonNull(managedCountry)) {
			CountryStatistics managedStat = managedCountry.getStatistics();
			CountryStatistics stat = country.getStatistics();
			managedStat.setSurfaceArea(stat.getSurfaceArea());
			managedStat.setPopulation(stat.getPopulation());
			managedStat.setGnp(stat.getGnp());
//			em.merge(managedCountry);
//			em.flush();
		}
	}

	@Override
	@Transactional
	public Optional<Country> removeById(String code) {
		Country managedCountry = em.find(Country.class, code);
		if (Objects.nonNull(managedCountry))
			em.remove(managedCountry);
		return Optional.ofNullable(managedCountry);
	}

	@Override
	@Transactional
	public void remove(Country country) {
		removeById(country.getCode());
	}

	@Override
	public Set<String> findAllContinents() {
		return new HashSet<>(em.createNamedQuery("Country.findDistinctContinent", String.class).getResultList());
	}

	@Override
	public List<Country> findAllByContinent(String continent) {
		return em.createNamedQuery("Country.findAllByContinent", Country.class)
			   .setParameter("continent", continent)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CountryCapital> findAllCapitalsByContinent(String continent) {
		List<Object[]> resultList = em.createNamedStoredProcedureQuery("ContinentCapitals")
		  .setParameter("cont", continent)
		  .getResultList();		
		return resultList.stream().map(record ->{
			CountryCapital cc = new CountryCapital();
			cc.setCode(record[0].toString());
			cc.setName(record[1].toString());
			cc.setCityId(Long.parseLong(record[2].toString()));
			cc.setCapital(record[3].toString());
			return cc;		
		}).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CountryCapital> findAllNativeCapitalsByContinent(String continent) {
		return em.createNamedQuery("ContinentCapitals")
				 .setParameter(1, continent)
				 .getResultList();
	}

}
