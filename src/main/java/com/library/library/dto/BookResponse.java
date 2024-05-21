package com.library.library.dto;

import com.library.library.domain.Book;

import lombok.Getter;

@Getter
public class BookResponse {

    private final String isbnNo;
    private final String title;
    private final String author;
    private final String publish;
    private final String pubyear;
    private final String num;
    private final String location;
    private final String imageSrc;
    private final String detailSrc;
    private final String tags;

    public BookResponse(Book book) {
        this.isbnNo = book.getIsbnNo();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publish = book.getPublish();
        this.pubyear = book.getPubyear();
        this.num = book.getNum();
        this.location = book.getLocation();
        this.imageSrc = book.getImageSrc();
        this.detailSrc = book.getDetailSrc();
        this.tags = book.getTags();
    }

}
