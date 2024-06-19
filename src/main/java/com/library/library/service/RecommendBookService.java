package com.library.library.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.library.domain.RecommendBook;
import com.library.library.domain.User;
import com.library.library.dto.AddRecommendRequest;
import com.library.library.repository.RecommendBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendBookService {

    private final RecommendBookRepository recommendBookRepository;

    public RecommendBook save(AddRecommendRequest request) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            recommendBookRepository.deleteById(loginUser.getStudentID());
        } catch (Exception e) {
        }
        return recommendBookRepository.save(request.toRecommendBook());
    }

    public RecommendBook findByStudentID(String studentID) {
        return recommendBookRepository.findByStudentID(studentID);
    }
}
