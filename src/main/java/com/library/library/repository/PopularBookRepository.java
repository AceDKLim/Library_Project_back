package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.PopularBook;

public interface PopularBookRepository extends JpaRepository<PopularBook, String> {

    List<PopularBook> findAllByOrderByCountDesc();
}
