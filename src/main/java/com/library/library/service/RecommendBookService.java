package com.library.library.service;

import org.springframework.stereotype.Service;

import com.library.library.domain.RecommendBook;
import com.library.library.repository.RecomendBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendBookService {

    private final RecomendBookRepository recomendBookRepository;

    public RecommendBook findbyStudentID(String studentID) {
        return recomendBookRepository.findByStudentID(studentID);
    }

}
