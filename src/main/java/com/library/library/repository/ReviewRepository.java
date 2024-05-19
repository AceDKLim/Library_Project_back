package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByIsbnNo(String isbnNo);

    List<Review> findByStudentNumber(String studentNumber);
}
