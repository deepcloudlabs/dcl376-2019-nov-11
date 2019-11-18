package com.example.imdb.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.example.imdb.entity.Movie;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	String SELECT_MOVIE_BY_ID = 
			"SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES WHERE MOVIEID=?";
	String SELECT_MOVIE_BY_IMDB = 
			"SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES WHERE IMDB=?";
	String SELECT_MOVIES_PAGINATION = 
			"SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES LIMIT ?,?";
	String SELECT_MOVIES_BY_YEAR_BETWEEN = 
			"SELECT MOVIEID,TITLE,YEAR,IMDB FROM MOVIES WHERE YEAR BETWEEN ? AND ?";
	String INSERT_INTO_MOVIES =
			"INSERT INTO MOVIES(TITLE,YEAR,IMDB) VALUES(?,?,?)";
	String UPDATE_MOVIE =
			"UPDATE MOVIES set TITLE=?,YEAR=?,IMDB=? where MOVIEID=?";
	String DELETE_MOVIE_BY_MOVIEID =
			"DELETE FROM MOVIES where MOVIEID=?";
	Stream<Movie> findAllByYearBetween(int from,int to);
	Optional<Movie> findOneByImdb(String imdb);
}
