package com.library.library.dto;

import com.library.library.domain.PopularBook;

import lombok.Getter;

@Getter
public class PopularBookResponse {

    private final String isbnNo;
    private final int count;

    public PopularBookResponse(PopularBook popularBook) {
        this.isbnNo = popularBook.getIsbnNo();
        this.count = popularBook.getCount();
    }

}
