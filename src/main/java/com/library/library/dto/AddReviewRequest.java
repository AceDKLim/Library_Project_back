package com.library.library.dto;

import org.springframework.security.core.context.SecurityContextHolder;

import com.library.library.domain.Review;
import com.library.library.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {

    User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    private String title;
    private String content;
    private String isbnNo;
    private String score;

    public Review toReview() {
        return Review.builder()
                .title(title)
                .content(content)
                .isbnNo(isbnNo)
                .studentNumber(login_user.getStudentID())
                .score(score)
                .build();
    }
}
