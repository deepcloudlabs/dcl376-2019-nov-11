package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, String> imdb;
	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile ListAttribute<Movie, Genre> genres;
	public static volatile ListAttribute<Movie, Director> directors;
	public static volatile SingularAttribute<Movie, Long> id;
	public static volatile SingularAttribute<Movie, String> title;

	public static final String IMDB = "imdb";
	public static final String YEAR = "year";
	public static final String GENRES = "genres";
	public static final String DIRECTORS = "directors";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

