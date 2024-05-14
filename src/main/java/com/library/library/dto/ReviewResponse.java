package com.library.library.dto;

import com.library.library.domain.Review;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private final String title;
    private final String content;
    private final String isbn_no;
    private final String student_number;
    private final String score;

    public ReviewResponse(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.isbn_no = review.getIsbn_no();
        this.student_number = review.getStudent_number();
        this.score = review.getScore();
    }
}
