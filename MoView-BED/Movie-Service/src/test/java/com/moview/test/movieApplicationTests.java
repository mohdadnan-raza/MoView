package com.moview.test;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.moview.movie.Entity.Movie;
import com.moview.movie.Repository.MovieRepository;
import com.moview.movie.Service.MovieServices;

@SpringBootTest(classes=com.moview.movie.MovieApplication.class)
@ExtendWith(MockitoExtension.class)
class movieApplicationTests {
	
	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieServices movieServices;

	@Test
	void contextLoads() {
	}
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
    	Movie mov = new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test");
        Mockito
        .when(movieRepository.findByMovieName("test"))
        .thenReturn(mov);
        
     ResponseEntity<?> mov1 = movieServices.getMovieByMovieName("test");
        Assertions.assertEquals(mov, mov1.getBody());
    }
	 
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject2() {
    	Optional<Movie> mov = Optional.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findById(32L))
        .thenReturn(mov);
        
     ResponseEntity<?> mov1 = movieServices.findMovieById(32L);
        Assertions.assertEquals(mov, mov1.getBody());
    }
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject3() {
    	List<Movie> mov = List.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findAll())
        .thenReturn( mov);
        
     ResponseEntity<?> mov1 = movieServices.MovieList();
        Assertions.assertEquals(mov, mov1.getBody());
    }
	 
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject4() {
    	List<Movie> mov = List.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findAll())
        .thenReturn( mov);
        
     ResponseEntity<?> mov1 = movieServices.getMovieByGenres("test");
        Assertions.assertEquals(mov, mov1.getBody());
    }
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject5() {
    	List<Movie> mov = List.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findAll())
        .thenReturn( mov);
        
     ResponseEntity<?> mov1 = movieServices.getMovieByDirector("test");
        Assertions.assertEquals(mov, mov1.getBody());
    }
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject6() {
    	List<Movie> mov = List.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findAll())
        .thenReturn( mov);
        
     ResponseEntity<?> mov1 = movieServices.getMovieByLanguage("test");
        Assertions.assertEquals(mov, mov1.getBody());
    }
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject7() {
    	List<Movie> mov = List.of(new Movie(32L, "test", "test", "test", "test", "20","test", "test",100L ,"test"));
        Mockito
        .when(movieRepository.findAll())
        .thenReturn( mov);
        
     ResponseEntity<?> mov1 = movieServices.getMovieByCast("test");
        Assertions.assertEquals(mov, mov1.getBody());
    }
	
}
