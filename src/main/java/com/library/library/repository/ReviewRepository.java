package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
