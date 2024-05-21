package com.library.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Review;
import com.library.library.domain.User;
import com.library.library.dto.AddReviewRequest;
import com.library.library.dto.ReviewResponse;
import com.library.library.dto.UpdateReviewRequest;
import com.library.library.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addReviw(@RequestBody AddReviewRequest request) {
        Review savedReview = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/api/reviews/{isbnNo}")
    public ResponseEntity<List<ReviewResponse>> findReviews(@PathVariable String isbnNo) {
        List<ReviewResponse> reviews = reviewService.findReviews(isbnNo).stream().map(ReviewResponse::new).toList();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewResponse>> findMyReviews() {
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReviewResponse> reviews = reviewService.findMyReviews(login_user.getStudentID()).stream().map(ReviewResponse::new).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(reviews);
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewService.findReview(id);
        if (review.getStudentNumber().equals(login_user.getStudentID())) {
            reviewService.delete(id);
            return ResponseEntity.ok().body(review);
        }
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody UpdateReviewRequest request) {
        Review review = reviewService.findReview(id);
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (review.getStudentNumber().equals(login_user.getStudentID())) {
            Review updatReview = reviewService.update(id, request);
            return ResponseEntity.ok().body(updatReview);
        }
        return ResponseEntity.accepted().build();
    }
}
