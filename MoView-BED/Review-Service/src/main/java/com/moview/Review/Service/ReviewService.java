package com.moview.Review.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moview.Review.Entity.Review;
import com.moview.Review.Repository.ReviewRepository;
import com.moview.exception.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repo;

	// review list
	public ResponseEntity<?> listAll() {
		try {
			List<Review> list = repo.findAll();
			if (!list.isEmpty())
				return new ResponseEntity<>(list, HttpStatus.OK);
			else if (list.isEmpty())
				return new ResponseEntity<>("No Reviews Available", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Unable to access");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("Unable to access", HttpStatus.BAD_REQUEST);
		}
	}

	// delete review
	public ResponseEntity<?> delete(long id) {
		try {
			if (repo.findById(id).isPresent()) {
				repo.deleteById(id);
				if (!repo.findById(id).isPresent())
					return new ResponseEntity<>("Deleted", HttpStatus.OK);
				else if (repo.findById(id).isPresent())
					return new ResponseEntity<>("Review is Not Deleted", HttpStatus.NOT_FOUND);
				else
					throw new ResourceNotFoundException("Review is Not Deleted");
			} else if (!repo.findById(id).isPresent())
				return new ResponseEntity<>("Review is Not Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is Not Found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("Review is Not Found", HttpStatus.BAD_REQUEST);
		}
	}

	// save review
	public ResponseEntity<?> saveReview(Review review) {
		try {
			List<Review> reviews = repo.findByUsername(review.getUsername());
			reviews = reviews.stream().filter(x -> x.getMovieId() == review.getMovieId()).collect(Collectors.toList());
			if (reviews.isEmpty()) {
				Review rev = new Review();
				rev.setUsername(review.getUsername());
				rev.setMovieId(review.getMovieId());
				rev.setMovieName(review.getMovieName());
				rev.setRating(review.getRating());
				rev.setComment(review.getComment());
				repo.save(rev);
				if (repo.existsById(rev.getId()))
					return new ResponseEntity<>("Review Added", HttpStatus.OK);
				else
					return new ResponseEntity<>("Review is Not Added", HttpStatus.OK);
			} else if (!reviews.isEmpty())
				return new ResponseEntity<>("Review already exist for this movie by username: " + review.getUsername(),
						HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Review is not added");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// update review
	public ResponseEntity<?> updateReview(Review review, long id) {
		try {
			@SuppressWarnings("deprecation")
			Review rev = repo.getById(id); // .orElseThrow(() -> new AttributeNotFoundException());
			rev.setUsername(review.getUsername());
			rev.setMovieId(review.getMovieId());
			rev.setRating(review.getRating());
			rev.setComment(review.getComment());

			repo.save(rev);
			if (repo.existsById(rev.getId()))
				return new ResponseEntity<>("Review Updated", HttpStatus.OK);
			else if (!repo.existsById(rev.getId()))
				return new ResponseEntity<>("Review is Not Updated", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not Updated");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("Review is not Updated", HttpStatus.BAD_REQUEST);
		}
	}

	// find review by Id
	public ResponseEntity<?> findReviewById(Long id) {
		try {
			Optional<Review> rev = repo.findById(id);
			if (rev.isEmpty())
				return new ResponseEntity<>("No Review Found", HttpStatus.NOT_FOUND);
			else if (!rev.isEmpty())
				return new ResponseEntity<>(rev, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Review Found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("No Review Found", HttpStatus.BAD_REQUEST);
		}
	}

	// find review by movie Id
	public ResponseEntity<?> findReviewByMovieId(Long mId) {
		try {
			Predicate<Review> byMovieId = review -> review.getMovieId() == mId;
			List<Review> reviews = repo.findAll().stream().filter(byMovieId).collect(Collectors.toList());
			if (!reviews.isEmpty())
				return new ResponseEntity<>(reviews, HttpStatus.OK);
			else if (reviews.isEmpty())
				return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// find review by movie name
	public ResponseEntity<?> findReviewByMovieName(String movieName) {
		try {
			List<Review> rev = repo.findByMovieName(movieName);
			if (!rev.isEmpty())
				return new ResponseEntity<>(rev, HttpStatus.OK);
			else if (rev.isEmpty())
				return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// find reviews by username
	public ResponseEntity<?> findReviewsByUsername(String username) {
		try {
			List<Review> reviews = repo.findByUsername(username);
			if (!reviews.isEmpty())
				return new ResponseEntity<>(reviews, HttpStatus.OK);
			else if (reviews.isEmpty())
				return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// find review by username & movie-name
	public ResponseEntity<?> findReviewsByUsernameAndMovieName(String username, long mId) throws ResourceNotFoundException {
		try {
			List<Review> reviews = repo.findByUsername(username);
			Predicate<Review> byUsername = review -> review.getMovieId()==mId;
			Review rev = reviews.stream().filter(byUsername).findFirst().get();
			if (rev!=null)
				return new ResponseEntity<>(rev, HttpStatus.OK);
			else
				return new ResponseEntity<>("No Review Found", HttpStatus.NOT_FOUND);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("Review is not found", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//AvgRatings
	public ResponseEntity<?> avgRatingByMovieId() {
		try {
			List<Review> reviews = repo.findAll();
			List<Long> mId = repo.findAll().stream().map(x -> x.getMovieId()).distinct().collect(Collectors.toList());
			LinkedHashMap<Long, Double>  keyValue = new LinkedHashMap<Long, Double> ();
			for (Long i = 0L; i < Long.valueOf(mId.size()); i++) {
				Long j = i;
				Predicate<Review> byMovieId = review -> review.getMovieId() == mId.get(j.intValue());
			//filtering by movieId / get ratings
				List<Integer> ratings = reviews.stream().filter(byMovieId).map(x -> x.getRating()).toList();
				double avg = ratings.stream().mapToInt((a) -> a).summaryStatistics().getAverage();

				Long key = mId.get(j.intValue());
				
				keyValue.put(key, avg);
			}
			if (!keyValue.isEmpty())
				return new ResponseEntity<>(keyValue, HttpStatus.OK);
			else if (keyValue.isEmpty())
				return new ResponseEntity<>("No Rating Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No Rating Found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Best Movies
	public ResponseEntity<?> getBestMovies() {
		try {
			Predicate<Review> byRating = movie -> movie.getRating() >= 4;
			List<Long> reviews = repo.findAll().stream().filter(byRating).map(x -> x.getMovieId()).distinct()
					.collect(Collectors.toList());
			if (!reviews.isEmpty())
				return new ResponseEntity<>(reviews, HttpStatus.OK);
			else if (reviews.isEmpty())
				return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// Trending Movies
	public ResponseEntity<?> getTrendingMovies() {
		try {
			Object[] reviews = repo.findAll().stream().map(x -> x.getMovieId()).toArray();
			Map<Long, Long> elementCountMap = new LinkedHashMap<>();
			for (int i = 0; i < reviews.length; i++) {
				if (elementCountMap.containsKey(reviews[i]))
					elementCountMap.put((Long) reviews[i], elementCountMap.get(reviews[i]) + 1);
				else
					elementCountMap.put((Long) reviews[i], 1L);
			}
			ArrayList<Long> sortedElements = new ArrayList<>();
			elementCountMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
					.forEach(entry -> {
						for (int i = 1; i <= entry.getValue(); i++)
							sortedElements.add(entry.getKey());
					});
			List<Long> tml = sortedElements.stream().distinct().collect(Collectors.toList());
			if (!tml.isEmpty())
				return new ResponseEntity<>(tml, HttpStatus.OK);
			else if (tml.isEmpty())
				return new ResponseEntity<>("No Reviews Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Review is not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
