package com.moview.Review.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moview.Review.Entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

	Review getReviewById(long id);

	List<Review> findByMovieName(String movieName);

	List<Review> findByUsername(String username);

	
}
