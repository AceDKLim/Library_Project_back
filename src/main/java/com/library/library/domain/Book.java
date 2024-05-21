package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "book")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @Column(name = "isbnNo", nullable = false, unique = true)
    private String isbnNo;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publish", nullable = false)
    private String publish;

    @Column(name = "pubyear", nullable = false)
    private String pubyear;

    @Column(name = "num", nullable = false)
    private String num;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "imageSrc", nullable = false)
    private String imageSrc;

    @Column(name = "detailSrc", nullable = false)
    private String detailSrc;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Builder
    public Book(String isbnNo, String title, String author, String publish, String pubyear, String num, String location,
            String imageSrc, String detailSrc, String tags) {
        this.isbnNo = isbnNo;
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.pubyear = pubyear;
        this.num = num;
        this.location = location;
        this.imageSrc = imageSrc;
        this.detailSrc = detailSrc;
        this.tags = tags;
    }

    public void update(String isbnNo, String title, String author, String publish, String pubyear, String num,
            String location,
            String imageSrc, String detailSrc, String tags) {
        this.isbnNo = isbnNo;
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.pubyear = pubyear;
        this.num = num;
        this.location = location;
        this.imageSrc = imageSrc;
        this.detailSrc = detailSrc;
        this.tags = tags;
    }
}
