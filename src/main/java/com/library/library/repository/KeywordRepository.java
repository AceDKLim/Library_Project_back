package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.domain.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, String> {

    Keyword findByStudentID(String studentID);
}
