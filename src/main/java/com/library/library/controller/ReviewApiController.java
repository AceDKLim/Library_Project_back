package com.library.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final ReviewService bookService;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addBook(@RequestBody AddReviewRequest request) {
        Review savedBook = bookService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewResponse>> findAllReviews() {
        List<ReviewResponse> reviews = bookService.findAll().stream().map(ReviewResponse::new).toList();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<ReviewResponse> findReview(@PathVariable long id) {
        Review review = bookService.findById(id);
        return ResponseEntity.ok().body(new ReviewResponse(review));
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody UpdateReviewRequest request) {
        Review updatReview = bookService.update(id, request);
        return ResponseEntity.ok().body(updatReview);
    }
}
