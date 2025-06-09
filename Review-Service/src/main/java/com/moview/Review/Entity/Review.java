package com.moview.Review.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reviews")
public class Review {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "UserName")
	private String username;

	@Column(name = "MovieId")
	private Long movieId;

	@Column(name = "MovieName")
	private String movieName;

	@Column(name = "Rating")
	private int rating;

	@Column(name = "Comment")
	private String comment;

	public Review() {

	}

	public Review(long id, String username, Long movieId, String movieName, int rating, String comment) {
		super();
		this.id = id;
		this.username = username;
		this.movieId = movieId;
		this.movieName = movieName;
		this.rating = rating;
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}