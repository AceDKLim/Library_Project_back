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
    private String isbn_no;
    private String student_number;
    private String score;
}
