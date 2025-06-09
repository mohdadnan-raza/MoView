package com.moview.test;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moview.movie.Controller.MovieController;
import com.moview.movie.Entity.Movie;
import com.moview.movie.Repository.MovieRepository;
import com.moview.movie.Service.MovieServices;

@RunWith(MockitoJUnitRunner.class)
public class movieControllerTests {

	@InjectMocks
	private MovieServices movieService;

	@InjectMocks
	private MovieController movieController;

	@Mock
	private MovieRepository movieRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenGivenId_shouldDeleteUser_ifFound() {
		Movie movie = new Movie(32L, "test", "test", "test", "test", "20", "test", "test", 100L, "test");

		Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
		
		movieService.deleteMovie(movie.getId());
		verify(movieRepository).deleteById(movie.getId());
		
		//Assertions.assertEquals(HttpStatus.OK.value(), movieService.deleteMovie(movie.getId()).getStatusCodeValue());
	}

//	@Test()
//	public void should_throw_exception_when_user_doesnt_exist() {
//		
//		Movie movie = new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test");
//	given(movieRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
//	movieService.deleteMovie(movie.getId());
//	}


}