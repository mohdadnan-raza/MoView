package com.moview.Review;

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

import com.moview.Review.Entity.Review;
import com.moview.Review.Repository.ReviewRepository;
import com.moview.Review.Service.ReviewService;

@SpringBootTest(classes=com.moview.Review.ReviewApplication.class)
@ExtendWith(MockitoExtension.class)
class ReviewApplicationTests {
	
	@Mock
	private ReviewRepository reviewRepository;

	@InjectMocks
	private ReviewService reviewService;

	@Test
	void contextLoads() {
	}


	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
		
		List<Review> rev = List.of(new Review(32L, "test", 7L, "test", 4, "Awsm movie"), 
				new Review(32L, "test", 7L, "test", 4, "Awsm movie"), 
				new Review(32L, "test", 7L, "test", 4, "Awsm movie"));

        Mockito
        .when(reviewRepository.findByMovieName("test"))
        .thenReturn(rev);
        
     ResponseEntity<?> rev1 = reviewService.findReviewByMovieName("test");
        Assertions.assertEquals(rev, rev1.getBody());
    }
	 
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject2() {
    	Optional<Review> rev = Optional.of(new Review(32L, "test", 7L, "test", 4, "Awsm movie"));
        Mockito
        .when(reviewRepository.findById(32L))
        .thenReturn(rev);
        
     ResponseEntity<?> rev1 = reviewService.findReviewById(32L);
        Assertions.assertEquals(rev, rev1.getBody());
    }
	
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject3() {
		
		List<Review> rev = List.of(new Review(32L, "test", 7L, "test", 4, "Awsm movie"), 
				new Review(32L, "test", 7L, "test", 4, "Awsm movie"), 
				new Review(32L, "test", 7L, "test", 4, "Awsm movie"));

        Mockito
        .when(reviewRepository.findAll())
        .thenReturn(rev);
        
     ResponseEntity<?> rev1 = reviewService.listAll();
        Assertions.assertEquals(rev, rev1.getBody());
    }
	 
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject4() {
    	List<Review> rev = List.of(new Review(32L, "test", 7L, "test", 4, "Awsm movie"));
        Mockito
        .when(reviewRepository.findAll())
        .thenReturn(rev);
        
     ResponseEntity<?> rev1 = reviewService.findReviewByMovieId(7L);
        Assertions.assertEquals(rev, rev1.getBody());
    } 
	
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject5() {
    	List<Review> rev = List.of(new Review(32L, "test", 7L, "test", 4, "Awsm movie"));
        Mockito
        .when(reviewRepository.findByUsername("test"))
        .thenReturn(rev);
        
     ResponseEntity<?> rev1 = reviewService.findReviewsByUsername("test");
        Assertions.assertEquals(rev, rev1.getBody());
    } 
}