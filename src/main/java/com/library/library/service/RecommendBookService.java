package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.dto.RecommendBookResponse;
import com.library.library.repository.RecomendBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendBookService {

    private final RecomendBookRepository recomendBookRepository;

    public List<RecommendBookResponse> findbyStudentID(String studentID) {
        return recomendBookRepository.findAllByStudentID(studentID).stream().map(RecommendBookResponse::new).toList();
    }

}
