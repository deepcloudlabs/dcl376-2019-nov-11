package com.example.imdb.repository.orm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.entity.Movie;
import com.example.imdb.repository.MovieRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Repository
public class JpaMovieRepository implements MovieRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Movie> findOneById(Integer id) {
		return Optional.ofNullable(em.find(Movie.class, id));
	}

	@Override
	public List<Movie> findAll(int pageNo, int pageSize) {
		return em.createNamedQuery("Movie.findAll", Movie.class).setFirstResult(pageNo * pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public Optional<Movie> save(Movie movie) {
		em.persist(movie);
		return Optional.of(movie);
	}

	@Override
	@Transactional
	public Optional<Movie> update(Movie movie) {
		Integer id = movie.getMovieId();
		Optional<Movie> optionalOfMovie = this.findOneById(id);
		optionalOfMovie.ifPresent(managed -> {
			managed.setTitle(movie.getTitle());
			managed.setYear(movie.getYear());
		});
		return optionalOfMovie;
	}

	@Override
	@Transactional
	public Optional<Movie> removeById(Integer id) {
		Optional<Movie> movie = this.findOneById(id);
		movie.ifPresent(managed -> {
			em.remove(managed);
		});
		return movie;
	}

	@Override
	@Transactional
	public Optional<Movie> remove(Movie movie) {
		return this.removeById(movie.getMovieId());
	}

	@Override
	public Stream<Movie> findAllByYearBetween(int from, int to) {
		return em.createNamedQuery("Movie.findAllByYearBetween", Movie.class).setParameter("from", from)
				.setParameter("to", to).getResultList().stream();
	}

	@Override
	public Optional<Movie> findOneByImdb(String imdb) {
		// TODO Auto-generated method stub
		return null;
	}

}
