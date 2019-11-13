package com.example.world.repository.orm;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;

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
		return em.createNamedQuery("Country.findAll", Country.class)
				 .setFirstResult(pageNo*pageSize)
				 .setMaxResults(pageSize)
				.getResultList();
	}

	@Override
	public Stream<Country> findAllStream(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Country e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Country e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Country> removeById(String k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Country e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> findAllContinents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> findAllByContinent(String continent) {
		// TODO Auto-generated method stub
		return null;
	}

}
