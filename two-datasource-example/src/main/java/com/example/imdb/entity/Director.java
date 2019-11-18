package com.example.imdb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Entity
@Table(name="directors")
public class Director {
	@Id
	@Column(name="directorid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String name;
	private String imdb;

	@OneToMany()
	@JoinTable(
			name="moviedirectors",
			joinColumns={
				@JoinColumn(name="directorid",referencedColumnName="directorid")	
			},
			inverseJoinColumns={
					@JoinColumn(name="movieid",referencedColumnName="movieid")	
			}
	)	
	private List<Movie> movies;

	public Director() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Director other = (Director) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Director [id=" + id + ", name=" + name + ", imdb=" + imdb + "]";
	}
	
}
