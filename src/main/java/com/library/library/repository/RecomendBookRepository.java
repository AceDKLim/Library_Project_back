package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.RecommendBook;

public interface RecomendBookRepository extends JpaRepository<RecommendBook, String> {

    RecommendBook findByStudentID(String studentID);
}
