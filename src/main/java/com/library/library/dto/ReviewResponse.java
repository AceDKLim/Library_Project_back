package com.library.library.dto;

import com.library.library.domain.Review;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private final String title;
    private final String content;
    private final String isbnNo;
    private final String studentnumber;
    private final String score;

    public ReviewResponse(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.isbnNo = review.getIsbnNo();
        this.studentnumber = review.getStudentNumber();
        this.score = review.getScore();
    }
}
