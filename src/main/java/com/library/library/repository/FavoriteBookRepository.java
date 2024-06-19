package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.FavoriteBook;

public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, Long> {

    List<FavoriteBook> findAllByStudentID(String studentID);

    void deleteByIsbnNoAndStudentID(String isbnNo, String studentID);

    List<FavoriteBook> findByStudentIDAndIsbnNo(String studentID, String isbnNo);
}
