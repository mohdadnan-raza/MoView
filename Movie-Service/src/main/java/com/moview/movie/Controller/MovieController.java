package com.moview.movie.Controller;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import com.moview.exception.ResourceNotFoundException;
import com.moview.movie.Entity.Movie;
import com.moview.movie.Service.MovieServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

	Logger LOGGER
    = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MovieServices services;
	
	@GetMapping("/") //movie
	public String Movie(){
		return "Movie!!";
	}

	@PostMapping("/movie/add") // Add movie
	public ResponseEntity<?> addMovie(@RequestBody Movie mov){
		return services.addMovie(mov);
	}

	@GetMapping("/movie/id/{id}") // Search Movie By movie-id
	public ResponseEntity<?> viewMovieById(@PathVariable Long id){

		ResponseEntity<?> mov = services.findMovieById(id);
		return mov;
	}

	@GetMapping("/movie/name/{movieName}") // Search Movie By Name
	public ResponseEntity<?> viewMovieByMovieName(@PathVariable String movieName){
		ResponseEntity<?> mov = services.getMovieByMovieName(movieName);
		return mov;
	}

	@GetMapping("/movie/list") // Movie List
	public ResponseEntity<?> viewMovieList() {
		return services.MovieList();
	}

	@PutMapping("/movie/update/id/{id}") // Update Movie
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable(name = "id") long movieId){
		ResponseEntity<?> mov = services.updateMovie(movie, movieId);
		return mov;
	}

	@DeleteMapping("/movie/delete/id/{id}") // Delete Movie
	public ResponseEntity<?> deleteMovie(@PathVariable long id){
		return services.deleteMovie(id);
	}

	// search by genres
	@GetMapping("/movie/genres/{genres}")
	public ResponseEntity<?> viewMovieByGenres(@PathVariable String genres){

		ResponseEntity<?> mov = services.getMovieByGenres(genres);
		return mov;
	}

	// search by cast
	@GetMapping("/movie/cast/{cast}")
	public ResponseEntity<?> viewMovieByCast(@PathVariable String cast){

		ResponseEntity<?> mov = services.getMovieByCast(cast);
		return mov;
	}

	// search by director
	@GetMapping("/movie/director/{director}")
	public ResponseEntity<?> viewMovieByDirector(@PathVariable String director) {

		ResponseEntity<?> mov = services.getMovieByDirector(director);
		return mov;
	}

	// search by Language
	@GetMapping("/movie/language/{language}")
	public ResponseEntity<?> viewMovieByLanguage(@PathVariable String language) {

		ResponseEntity<?> mov = services.getMovieByLanguage(language);
		return mov;
	}

	// Search Bar
	@GetMapping("/movie/search/{search}")
	public ResponseEntity<?> viewMovieSearch(@PathVariable String search) {

		ResponseEntity<?> mov = services.getMovieBySearch(search);
		return mov;
	}
	
	
	// Calling External API's

	// review by movie Id
	@GetMapping("/movie/review/movie-id/{id}") 
	@CircuitBreaker(name = "movieService", fallbackMethod = "MovieServiceFallback")
	//@Retry(name = "retryApi", fallbackMethod = "MovieServiceFallback")
	public List<Object> reviewByMovieId(@PathVariable long id) throws ResourceNotFoundException {
		String url = "http://review-service/review/movie-id/"+id;
		Object reviews = restTemplate.getForObject(url, Object.class);
		return Arrays.asList(reviews);
	}
	
	//trending movies
	@GetMapping("/trending/movies/list")
	//@CircuitBreaker(name = "movieService", fallbackMethod = "MovieServiceFallback")
	@Retry(name = "retryApi")
	public ResponseEntity<?> trendingMovies() throws ResourceNotFoundException {
		return services.findTrendMoviesByIds();
	}
	
	@GetMapping("/best/movies/list")
	//@CircuitBreaker(name = "movieService", fallbackMethod = "MovieServiceFallback")
	@Retry(name = "retryApi")
	public ResponseEntity<?> bestMovies() throws ResourceNotFoundException {
		return services.findBestMoviesByIds();
	}
	
	public String MovieServiceFallback(Exception ex) {
		return "Fallback: Go Back to Home";
	}

}
