package com.example.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imdb.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, CustomCriteriaMovieRepository {

}
