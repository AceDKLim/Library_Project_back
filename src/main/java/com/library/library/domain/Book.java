package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @Column(name = "isbn_no", nullable = false)
    private String isbn_no;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publish", nullable = false)
    private String publish;

    @Column(name = "p_year", nullable = false)
    private String p_year;

    @Column(name = "num", nullable = false)
    private String num;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "image_src", nullable = false)
    private String image_src;

    @Column(name = "detail_src", nullable = false)
    private String detail_src;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Builder
    public Book(String isbn_no, String title, String author, String publish, String p_year, String num, String location,
            String image_src, String detail_src, String tags) {
        this.isbn_no = isbn_no;
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.p_year = p_year;
        this.num = num;
        this.location = location;
        this.image_src = image_src;
        this.detail_src = detail_src;
        this.tags = tags;
    }

    public void update(String isbn_no, String title, String author, String publish, String p_year, String num, String location,
            String image_src, String detail_src, String tags) {
        this.isbn_no = isbn_no;
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.p_year = p_year;
        this.num = num;
        this.location = location;
        this.image_src = image_src;
        this.detail_src = detail_src;
        this.tags = tags;
    }
}
