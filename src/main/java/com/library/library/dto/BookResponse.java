package com.library.library.dto;

import com.library.library.domain.Book;

import lombok.Getter;

@Getter
public class BookResponse {

    private final String isbn_no;
    private final String title;
    private final String author;
    private final String publish;
    private final String p_year;
    private final String num;
    private final String location;
    private final String image_src;
    private final String detail_src;
    private final String tags;

    public BookResponse(Book book) {
        this.isbn_no = book.getIsbn_no();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publish = book.getPublish();
        this.p_year = book.getP_year();
        this.num = book.getNum();
        this.location = book.getLocation();
        this.image_src = book.getImage_src();
        this.detail_src = book.getDetail_src();
        this.tags = book.getTags();
    }

}
