package com.example.imdb.repository.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Genre_;
import com.example.imdb.entity.Movie;
import com.example.imdb.entity.Movie_;
import com.example.imdb.repository.CustomCriteriaMovieRepository;

@Repository
public class CustomCriteriaMovieRepositoryImpl implements CustomCriteriaMovieRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Movie> filminTuruneGoreAra(String tur) {
		System.err.println("CustomCriteriaMovieRepositoryImpl::filminTuruneGoreAra");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> movie = query.from(Movie.class);
		Join<Movie, Genre> join = movie.join(Movie_.genres);
		Predicate condition = cb.equal(join.get(Genre_.description), tur);
		query.select(movie).where(condition);
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Movie> filminYilinaVeAdinaGoreAra(int bas, int son, String ad) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);

		Root<Movie> movie = cq.from(Movie.class);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(
				cb.and(cb.greaterThanOrEqualTo(movie.get(Movie_.year), bas), cb.lessThan(movie.get(Movie_.year), son)));
		if (Objects.nonNull(ad)) {
			predicates.add(cb.like(movie.get(Movie_.title), "%" + ad + "%"));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		return em.createQuery(cq).getResultList();
	}

}
