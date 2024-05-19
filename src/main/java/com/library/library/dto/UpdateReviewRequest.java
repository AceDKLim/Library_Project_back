package com.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateReviewRequest {

    private String title;
    private String contents;
    private String isbnNo;
    private String studentNumber;
    private String score;
}
