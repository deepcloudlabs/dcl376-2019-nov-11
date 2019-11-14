package com.example.world.repository.orm;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
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
		return Optional.ofNullable(em.find(Country.class, code));
	}

	@Override
	public List<Country> findAll(int pageNo, int pageSize) {
		return em.createNamedQuery("Country.findAll", Country.class).setFirstResult(pageNo * pageSize)
				.setMaxResults(pageSize).getResultList();
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

}
