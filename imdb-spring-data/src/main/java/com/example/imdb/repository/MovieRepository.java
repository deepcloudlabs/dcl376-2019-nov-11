package com.example.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.imdb.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long>{

}
