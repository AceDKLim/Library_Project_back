package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByIsbn_no(String isbn_no);

    public List<Review> findByStudent_number(String student_number);
}
