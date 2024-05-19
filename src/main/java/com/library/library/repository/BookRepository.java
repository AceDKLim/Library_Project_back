package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT library_project.book WHERE book.title LIKE %:keyword% OR book.tags LIKE %:keyword% "
    )
    List<Book> findAllSearch(String keyword);
}
