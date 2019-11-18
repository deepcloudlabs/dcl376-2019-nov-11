package com.example.imdb.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
public class JdbcTemplateMovieRepository implements MovieRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Optional<Movie> findOneById(Integer id) {
		Movie movie = jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, new BeanPropertyRowMapper<Movie>(Movie.class),
				id);
		return Optional.ofNullable(movie);
	}

	@Override
	public List<Movie> findAll(int pageNo, int pageSize) {
		int offset = pageNo * pageSize;
		return jdbcTemplate.query(SELECT_MOVIES_PAGINATION, new Object[] { offset, pageSize },
				new BeanPropertyRowMapper<Movie>(Movie.class));
	}

	@Override
	public Stream<Movie> findAllByYearBetween(int from, int to) {
		return jdbcTemplate.query(SELECT_MOVIES_BY_YEAR_BETWEEN, new Object[] { from, to },
				new BeanPropertyRowMapper<Movie>(Movie.class)).stream();
	}

	@Override
	@Transactional
	public Optional<Movie> save(Movie movie) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int numOfInserted = jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(INSERT_INTO_MOVIES, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, movie.getTitle());
				ps.setInt(2, movie.getYear());
				ps.setString(3, movie.getImdb());
				return ps;
			}, keyHolder);
			if (numOfInserted > 0) {
				movie.setMovieId(keyHolder.getKey().intValue());
				return Optional.of(movie);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<Movie> update(Movie movie) {
		try {
			int numOfUpdated = jdbcTemplate.update(UPDATE_MOVIE, movie.getTitle(), movie.getYear(), movie.getImdb(),
					movie.getMovieId());
			if (numOfUpdated > 0) {
				return Optional.of(movie);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	@Transactional
	public Optional<Movie> removeById(Integer id) {
		try {
			Optional<Movie> movie = findOneById(id);
			if (movie.isPresent()) {
				int numOfUpdated = jdbcTemplate.update(DELETE_MOVIE_BY_MOVIEID, id);
				if (numOfUpdated > 0) {
					return movie;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public Optional<Movie> remove(Movie movie) {
		return removeById(movie.getMovieId());
	}

	@Override
	public Optional<Movie> findOneByImdb(String imdb) {
		Movie movie = jdbcTemplate.queryForObject(SELECT_MOVIE_BY_IMDB, new BeanPropertyRowMapper<Movie>(Movie.class),
				imdb);
		return Optional.ofNullable(movie);
	}

}
