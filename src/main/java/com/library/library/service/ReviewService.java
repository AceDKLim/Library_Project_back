package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.domain.Review;
import com.library.library.dto.AddReviewRequest;
import com.library.library.dto.UpdateReviewRequest;
import com.library.library.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review save(AddReviewRequest request) {
        return reviewRepository.save(request.toReview());
    }

    public Review findReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id.toString()));
    }

    public List<Review> findReviews(String isbnNo) {
        return reviewRepository.findByIsbnNo(isbnNo);
    }

    public List<Review> findMyReviews(String studentnumber) {
        return reviewRepository.findByStudentNumber(studentnumber);
    }

    public void delete(long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public Review update(long id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        review.update(request.getTitle(), request.getContents(), request.getIsbnNo(), request.getStudentNumber(), request.getScore());
        return review;
    }
}
