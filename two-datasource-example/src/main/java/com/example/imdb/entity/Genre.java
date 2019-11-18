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
@Table(name="genres")
public class Genre {
	@Id
	@Column(name="genreid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String description;

	@OneToMany()
	@JoinTable(
			name="moviegenres",
			joinColumns={
				@JoinColumn(name="genreid",referencedColumnName="genreid")	
			},
			inverseJoinColumns={
					@JoinColumn(name="movieid",referencedColumnName="movieid")	
			}
	)	
	private List<Movie> movies;

	public Genre() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Genre [description=" + description + "]";
	}
	
}
