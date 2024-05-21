package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.RecommendBook;

public interface RecomendBookRepository extends JpaRepository<RecommendBook, Long> {

    List<RecommendBook> findAllByStudentID(String studentID);
}
