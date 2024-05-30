package com.library.library.dto;

import org.springframework.security.core.context.SecurityContextHolder;

import com.library.library.domain.RecommendBook;
import com.library.library.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRecommendRequest {

    User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    private String tags;

    public RecommendBook toRecommendBook() {
        return RecommendBook.builder().studentID(login_user.getStudentID()).isbnNo(tags).build();
    }
}
