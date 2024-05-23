package com.library.library.dto;

import com.library.library.domain.Keyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordResponse {
    private final String studentID;
    private final String tags;

    public KeywordResponse(Keyword keyword) {
        this.studentID = keyword.getStudentID();
        this.tags = keyword.getTags();
    }
}
