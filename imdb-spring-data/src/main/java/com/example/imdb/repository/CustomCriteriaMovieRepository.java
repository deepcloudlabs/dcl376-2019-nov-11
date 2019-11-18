package com.example.imdb.repository;

import java.util.List;

import com.example.imdb.entity.Movie;

public interface CustomCriteriaMovieRepository {
	List<Movie> filminTuruneGoreAra(String tur);

	List<Movie> filminYilinaVeAdinaGoreAra(int bas, int son, String ad);
}
