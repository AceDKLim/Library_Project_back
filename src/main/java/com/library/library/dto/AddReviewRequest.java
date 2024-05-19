package com.library.library.dto;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.library.library.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserDetails userDetails = (UserDetails) principal;
    private String title;
    private String content;
    private String isbn_no;
    private String score;

    public Review toReview() {
        return Review.builder()
                .title(title)
                .content(content)
                .isbn_no(isbn_no)
                .student_number(userDetails.getUsername())
                .score(score)
                .build();
    }
}
