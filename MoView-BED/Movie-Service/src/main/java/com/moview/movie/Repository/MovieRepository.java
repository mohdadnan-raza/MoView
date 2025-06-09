package com.moview.movie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moview.movie.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	//Movie getMovieById(Long id);

	Movie findByMovieName(String movieName);
	

}