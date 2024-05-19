package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitleContaining(String keyword);
}
