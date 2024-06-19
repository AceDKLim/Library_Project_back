package com.library.library.dto;

import org.springframework.security.core.context.SecurityContextHolder;

import com.library.library.domain.FavoriteBook;
import com.library.library.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddFavoriteBookRequest {

    private String isbnNo;

    public FavoriteBook toFavoriteBook() {
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return FavoriteBook.builder().studentID(login_user.getStudentID()).isbnNo(isbnNo).build();
    }
}
