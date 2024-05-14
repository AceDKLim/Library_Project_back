package com.library.library.dto;

import com.library.library.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {

    private String title;
    private String content;
    private String isbn_no;
    private String student_number;
    private String score;

    public Review toReview() {
        return Review.builder()
                .title(title)
                .content(content).isbn_no(isbn_no).student_number(student_number).score(score)
                .build();
    }
}
