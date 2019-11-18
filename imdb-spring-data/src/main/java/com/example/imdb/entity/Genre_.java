package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Genre.class)
public abstract class Genre_ {

	public static volatile ListAttribute<Genre, Movie> movies;
	public static volatile SingularAttribute<Genre, String> description;
	public static volatile SingularAttribute<Genre, Integer> id;

	public static final String MOVIES = "movies";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

