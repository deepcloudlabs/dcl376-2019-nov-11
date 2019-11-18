package com.example.imdb.repository.orm;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Repository
public class JpaMovieRepository implements MovieRepository {
	@PersistenceContext(unitName = "imdbPU")
	private EntityManager entityManager;

	@Override
	public Optional<Movie> findOne(Long id) {
		return Optional.ofNullable(entityManager.find(Movie.class, id));
	}

	@Override
	public Collection<Movie> findAll() {
		return entityManager.createNamedQuery("Movie.findAll", Movie.class).getResultList();
	}

	@Override
	@Transactional
	public Movie add(Movie movie) {
		movie.setId(null);
		entityManager.persist(movie);
		return movie;
	}

	@Override
	@Transactional
	public Movie update(Movie movie) {
		Long id = movie.getId();
		Optional<Movie> found = findOne(id);
		if (found.isPresent()) {
			Movie m = found.get();
			m.setYear(movie.getYear());
			m.setImdb(movie.getImdb());
			entityManager.merge(m);
			return m;
		}
		throw new IllegalArgumentException("Movie does not exist!");
	}

	@Override
	@Transactional
	public Optional<Movie> remove(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		if (Objects.nonNull(movie)) {
			entityManager.remove(movie);
			Optional.of(movie);
		}
		return Optional.empty();
	}

}
