package com.library.library.dto;

import org.springframework.security.core.context.SecurityContextHolder;

import com.library.library.domain.Keyword;
import com.library.library.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddKeywordRequest {

    User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    private String tag1;
    private String tag2;

    public Keyword toKeyword() {
        return Keyword.builder().studentID(login_user.getStudentID()).tags(tag2).build();
    }
}
