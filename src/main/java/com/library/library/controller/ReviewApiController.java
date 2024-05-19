package com.library.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Review;
import com.library.library.dto.AddReviewRequest;
import com.library.library.dto.ReviewResponse;
import com.library.library.dto.UpdateReviewRequest;
import com.library.library.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserDetails userDetails = (UserDetails) principal;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addReviw(@RequestBody AddReviewRequest request) {
        Review savedBook = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/api/reviews/{isbn_no}")
    public ResponseEntity<List<ReviewResponse>> findReviews(@PathVariable String isbn_no) {
        List<ReviewResponse> reviews = reviewService.findReviews(isbn_no).stream().map(ReviewResponse::new).toList();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/api/reviews/")
    public ResponseEntity<List<ReviewResponse>> findMyReviews() {
        List<ReviewResponse> reviews = reviewService.findReviews(userDetails.getUsername()).stream().map(ReviewResponse::new).toList();
        return ResponseEntity.ok().body(reviews);
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {
        Review review = reviewService.findReview(id);
        if (review.getStudent_number().equals(userDetails.getUsername())) {
            reviewService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody UpdateReviewRequest request) {
        Review review = reviewService.findReview(id);
        if (review.getStudent_number().equals(userDetails.getUsername())) {
            Review updatReview = reviewService.update(id, request);
            return ResponseEntity.ok().body(updatReview);
        }
        return ResponseEntity.accepted().build();
    }
}
