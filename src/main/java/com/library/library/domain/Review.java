package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "review")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "isbnNo", nullable = false)
    private String isbnNo;

    @Column(name = "studentNumber", nullable = false)
    private String studentNumber;

    @Column(name = "score", nullable = false)
    private String score;

    @Builder
    public Review(String title, String content, String isbnNo, String studentNumber, String score) {
        this.title = title;
        this.content = content;
        this.isbnNo = isbnNo;
        this.studentNumber = studentNumber;
        this.score = score;
    }

    public void update(String title, String content, String isbnNo, String studentNumber, String score) {
        this.title = title;
        this.content = content;
        this.isbnNo = isbnNo;
        this.studentNumber = studentNumber;
        this.score = score;
    }
}
