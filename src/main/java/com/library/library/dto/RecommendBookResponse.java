package com.library.library.dto;

import com.library.library.domain.RecommendBook;

import lombok.Getter;

@Getter
public class RecommendBookResponse {

    private final String isbnNo;
    private final String studentID;

    public RecommendBookResponse(RecommendBook recommendBook) {
        this.isbnNo = recommendBook.getIsbnNo();
        this.studentID = recommendBook.getStudentID();
    }
}
