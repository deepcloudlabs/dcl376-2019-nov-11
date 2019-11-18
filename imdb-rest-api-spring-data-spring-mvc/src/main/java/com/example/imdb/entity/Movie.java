package com.example.imdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Entity
@Table(name = "movies")
@NamedQueries({ @NamedQuery(name = "Movie.findAll", query = "select m from Movie m"),
		@NamedQuery(name = "Movie.findAllByYearBetween", query = "select m from Movie m where m.year between :from and :to") })
@DynamicUpdate
@ApiModel(description = "All details about the Movie.")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieid")
	@ApiModelProperty(notes = "The movie id", example = "42")
	private Integer movieId;
	@Size(min = 3)
	@ApiModelProperty(notes = "The movie title", example = "The matrix")
	private String title;
	@ApiModelProperty(notes = "The movie year", example = "1999")
	private int year;
	@Pattern(regexp = "^tt\\d{6,8}")
	@ApiModelProperty(notes = "The movie imdb id", example = "tt0133093")
	private String imdb;

	public Movie() {
	}

	public Movie(String title, int year, String imdb) {
		this.title = title;
		this.year = year;
		this.imdb = imdb;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
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
		Movie other = (Movie) obj;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", imdb=" + imdb + "]";
	}

}