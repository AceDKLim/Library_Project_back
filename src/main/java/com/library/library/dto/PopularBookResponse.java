package com.library.library.dto;

import com.library.library.domain.PopularBook;

import lombok.Getter;

@Getter
public class PopularBookResponse {

    private final String isbn_no;
    private final int count;

    public PopularBookResponse(PopularBook popularBook) {
        this.isbn_no = popularBook.getIsbn_no();
        this.count = popularBook.getCount();
    }

}
