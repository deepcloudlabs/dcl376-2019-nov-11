package com.example.imdb.dto;

import java.util.List;

public class MovieDTO {
	private Long id;
	private String title;
	private String imdb;
	private int year;
	private List<String> genres;

	public MovieDTO() {
	}

	public MovieDTO(Long id, String title, String imdb, int year) {
		this.id = id;
		this.title = title;
		this.imdb = imdb;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", imdb=" + imdb + ", year=" + year + "]";
	}

}
