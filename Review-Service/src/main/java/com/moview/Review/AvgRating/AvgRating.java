package com.moview.Review.AvgRating;

public class AvgRating {
	
	private long movieId;

	private double AvgRating;
	
	public AvgRating() {
		
	}

	public AvgRating(long movieId, double avgRating) {
		super();
		this.movieId = movieId;
		AvgRating = avgRating;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public double getAvgRating() {
		return AvgRating;
	}

	public void setAvgRating(double avgRating) {
		AvgRating = avgRating;
	}
	
	

}
