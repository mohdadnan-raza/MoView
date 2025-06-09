package com.moview.movie.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class Movie {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String movieName;
	private String movieDescription;
	private String movieImage;
	private String movieGenres;
	private String movieReleaseYear;
	private String movieLang;
	private String movieCast;
	private long movieRunTime;
	private String movieDirector;
	
	public Movie() {
		
	}

	public Movie(Long id, String movieName, String movieDescription, String movieImage, String movieGenres,
			String movieReleaseYear, String movieLang, String movieCast, long movieRunTime, String movieDirector) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.movieDescription = movieDescription;
		this.movieImage = movieImage;
		this.movieGenres = movieGenres;
		this.movieReleaseYear = movieReleaseYear;
		this.movieLang = movieLang;
		this.movieCast = movieCast;
		this.movieRunTime = movieRunTime;
		this.movieDirector = movieDirector;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(String movieImage) {
		this.movieImage = movieImage;
	}

	public String getMovieReleaseYear() {
		return movieReleaseYear;
	}

	public void setMovieReleaseYear(String movieReleaseYear) {
		this.movieReleaseYear = movieReleaseYear;
	}

	public String getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(String movieGenres) {
		this.movieGenres = movieGenres;
	}

	public String getMovieLang() {
		return movieLang;
	}

	public void setMovieLang(String movieLang) {
		this.movieLang = movieLang;
	}

	public String getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}

	public long getMovieRunTime() {
		return movieRunTime;
	}

	public void setMovieRunTime(long movieRunTime) {
		this.movieRunTime = movieRunTime;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	
	
}

