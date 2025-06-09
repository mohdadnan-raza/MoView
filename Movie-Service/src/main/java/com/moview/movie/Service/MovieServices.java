package com.moview.movie.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moview.exception.ResourceNotFoundException;
import com.moview.movie.Entity.Movie;
import com.moview.movie.Repository.MovieRepository;

@Service
public class MovieServices {

	@Autowired
	private MovieRepository repo;

	@Autowired
	private RestTemplate restTemplate;

	// Find By Movie Id
	public ResponseEntity<?> findMovieById(Long id) {
			Optional<Movie> movie = repo.findById(id);
			if (movie.isEmpty())
				return new ResponseEntity<>("No Movie Found for Movie id: " + id, HttpStatus.NOT_FOUND);
			else if (!movie.isEmpty())
				return new ResponseEntity<Optional<Movie>>(movie, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("Something went wrong");
	}

	// Add Movie
	public ResponseEntity<?> addMovie(Movie mov) {
		try {
			Movie movie = repo.save(mov);
			if (repo.existsById(movie.getId()))
				return new ResponseEntity<>("Movie Added", HttpStatus.OK);
			else if (!repo.existsById(movie.getId()))
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Movie is not added");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// Movie List
	public ResponseEntity<?> MovieList() throws ResourceNotFoundException {
		try {
			List<Movie> movies = repo.findAll();
			if (movies != null)
				return new ResponseEntity<>(movies, HttpStatus.OK);
			else
				return new ResponseEntity<>("No Movie record exist", HttpStatus.NOT_FOUND);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Delete Movie by Id
	public ResponseEntity<?> deleteMovie(long id) {
		try {
			if (repo.findById(id).isPresent()) {
				repo.deleteById(id);
				return new ResponseEntity<>("Movie Deleted", HttpStatus.OK);
			} else if (!repo.findById(id).isPresent())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("Movie not found");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Update Movie by Id
	public ResponseEntity<?> updateMovie(Movie movie, long id) {
		try {
			if (repo.findById(id).isPresent()) {
				repo.save(movie);
				return new ResponseEntity<>("Movie details are updated", HttpStatus.OK);
			} else if (!repo.findById(id).isPresent())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>("Movie details are Not updated", HttpStatus.BAD_REQUEST);
		}
	}

	// by Movie-Name
	public ResponseEntity<?> getMovieByMovieName(String movieName) throws ResourceNotFoundException {

		try {
			Movie movie = repo.findByMovieName(movieName);

			if (movie != null)
				return new ResponseEntity<>(movie, HttpStatus.OK);
			else
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// genres
	public ResponseEntity<?> getMovieByGenres(String genres) {
		try {
			List<Movie> movies = repo.findAll();
			Predicate<Movie> byGenres = movie -> movie.getMovieGenres().toUpperCase().contains(genres.toUpperCase());
			movies = movies.stream().filter(byGenres).collect(Collectors.toList());
			if (movies.isEmpty())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else if (!movies.isEmpty())
				return new ResponseEntity<>(movies, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// cast
	public ResponseEntity<?> getMovieByCast(String cast) {

		try {
			List<Movie> movies = repo.findAll();
			Predicate<Movie> byCast = movie -> movie.getMovieCast().toUpperCase().contains(cast.toUpperCase());
			movies = movies.stream().filter(byCast).collect(Collectors.toList());
			if (movies.isEmpty())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else if (!movies.isEmpty())
				return new ResponseEntity<>(movies, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// director
	public ResponseEntity<?> getMovieByDirector(String director) {
		try {
			List<Movie> movies = repo.findAll();
			Predicate<Movie> byDirector = movie -> movie.getMovieDirector().toUpperCase()
					.contains(director.toUpperCase());
			movies = movies.stream().filter(byDirector).collect(Collectors.toList());
			if (movies.isEmpty())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else if (!movies.isEmpty())
				return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
	}

	// language
	public ResponseEntity<?> getMovieByLanguage(String language) {
		try {
			List<Movie> movies = repo.findAll();
			Predicate<Movie> byLanguage = movie -> movie.getMovieLang().toUpperCase().contains(language.toUpperCase());
			movies = movies.stream().filter(byLanguage).collect(Collectors.toList());
			if (movies.isEmpty())
				return new ResponseEntity<>("No Movie Found", HttpStatus.NOT_FOUND);
			else if (!movies.isEmpty())
				return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Search Bar 
	public ResponseEntity<?> getMovieBySearch(String search) {
		try {
			List<Movie> movies = repo.findAll();	//getting all movies
			
			//predicates
			Predicate<Movie> byName = movie -> movie.getMovieName().toUpperCase().contains(search.toUpperCase());
			Predicate<Movie> byGenres = movie -> movie.getMovieGenres().toUpperCase().contains(search.toUpperCase());
			Predicate<Movie> byCast = movie -> movie.getMovieCast().toUpperCase().contains(search.toUpperCase());
			Predicate<Movie> byDirector = movie -> movie.getMovieDirector().toUpperCase().contains(search.toUpperCase());
			Predicate<Movie> byLanguage = movie -> movie.getMovieLang().toUpperCase().contains(search.toUpperCase());
			
			//filtering final search results
			List<Movie> finalResults = movies.stream()
											.filter(byName.or(byGenres).or(byCast)
													.or(byDirector).or(byLanguage))
											.collect(Collectors.toList());

			if (finalResults.isEmpty())			//if the search result is empty then return NOT FOUND
				return new ResponseEntity<>("No Result Found", HttpStatus.NOT_FOUND);
			else if (!finalResults.isEmpty())	//if the search result is NOT empty then return 200 OK
				return new ResponseEntity<List<Movie>>(finalResults, HttpStatus.OK);
			else								//if anything goes wrong then throw exception
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {		//catching exception and return 
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Trending Movies
	public ResponseEntity<?> findTrendMoviesByIds() throws ResourceNotFoundException {
		try {
			String url = "http://review-service/trending/movies";

			List<Integer> ids = restTemplate.getForObject(url, List.class);

			List<Optional<Movie>> mov = new ArrayList<Optional<Movie>>();
			for (Long i = 0L; i < Long.valueOf(ids.size()); i++) {
				mov.add(repo.findById(Long.valueOf(ids.get(i.intValue()))));
			}
			if (!mov.isEmpty())
				return new ResponseEntity<>(mov, HttpStatus.OK);
			else
				return new ResponseEntity<>("No Movie record exist", HttpStatus.NOT_FOUND);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
	}

	// Best Movies
	public ResponseEntity<?> findBestMoviesByIds() {
		try {
			String url = "http://review-service/best/movies";

			List<Integer> ids = restTemplate.getForObject(url, List.class);

			List<Optional<Movie>> mov = new ArrayList<Optional<Movie>>();

			for (Long i = 0L; i < Long.valueOf(ids.size()); i++) {
				mov.add(repo.findById(Long.valueOf(ids.get(i.intValue()))));
			}

			if (!mov.isEmpty())
				return new ResponseEntity<>(mov, HttpStatus.OK);
			else
				throw new ResourceNotFoundException("No Movie record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<List<Movie>>(HttpStatus.BAD_REQUEST);
		}
	}
}
