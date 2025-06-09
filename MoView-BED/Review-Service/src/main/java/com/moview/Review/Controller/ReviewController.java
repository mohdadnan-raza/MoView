package com.moview.Review.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moview.Review.Entity.Review;
import com.moview.Review.Service.ReviewService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
	
	Logger LOGGER
    = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService services;
	
	@GetMapping("/") //review
	public String Review(){
		return "Review Service!!";
	}

	@PostMapping("/review/add") // Add Review
	private ResponseEntity<?> saveReview(@RequestBody Review review) {
		return services.saveReview(review);
	}

	@GetMapping("/review/list") // Review List
	public ResponseEntity<?> ReviewList() {
		return services.listAll();
	}

	@GetMapping("/review/id/{id}") // Review by Id
	public ResponseEntity<?> viewReviewById(@PathVariable Long id) {

		return services.findReviewById(id);
	}

	@PutMapping("/review/update/id/{id}") // Update Review By Id
	public ResponseEntity<?> updateReview(@RequestBody Review review, @PathVariable(name = "id") long reviewId) {
		ResponseEntity<?> rev = services.updateReview(review, reviewId);
		return rev;
	}

	@DeleteMapping("/review/delete/id/{id}") // Delete Review
	private ResponseEntity<?> deleteReview(@PathVariable("id") Long id) {
		return services.delete(id);
	}

	@GetMapping("/review/movie-id/{movieId}") // Review by Movie-Id
	public ResponseEntity<?> ReviewByMovieId(@PathVariable Long movieId) {
		return services.findReviewByMovieId(movieId);
	}

	@GetMapping("/review/movie-name/{movieName}") // Review by Movie-Name
	public ResponseEntity<?> ReviewByMovieId(@PathVariable String movieName) {
		return services.findReviewByMovieName(movieName);
	}

	@GetMapping("/review/username/{username}") // Review by Username
	public ResponseEntity<?> ReviewByUsername(@PathVariable String username) {
		return services.findReviewsByUsername(username);
	}
	
	@GetMapping("/review/username/{username}/movieId/{mId}") // Review by username & movieName
	public ResponseEntity<?> ReviewByUsernameAndMovieName(@PathVariable String username, 
															@PathVariable long mId) {
		return services.findReviewsByUsernameAndMovieName(username, mId);
	}

	@GetMapping("/average-rating") // Average Ratings
	public ResponseEntity<?> getAvgRatingByMovieId() {
		return services.avgRatingByMovieId();
	}

	// Best Movies
	@GetMapping("/best/movies")
	public ResponseEntity<?> bestMovies() {
		return services.getBestMovies();
	}

	// Trending Movies
	@GetMapping("/trending/movies")
	public ResponseEntity<?> trendingMovies() {
		return services.getTrendingMovies();
	}

}
