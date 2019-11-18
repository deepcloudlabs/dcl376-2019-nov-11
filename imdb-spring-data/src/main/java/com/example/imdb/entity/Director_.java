package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Director.class)
public abstract class Director_ {

	public static volatile ListAttribute<Director, Movie> movies;
	public static volatile SingularAttribute<Director, String> imdb;
	public static volatile SingularAttribute<Director, String> name;
	public static volatile SingularAttribute<Director, Integer> id;

	public static final String MOVIES = "movies";
	public static final String IMDB = "imdb";
	public static final String NAME = "name";
	public static final String ID = "id";

}

