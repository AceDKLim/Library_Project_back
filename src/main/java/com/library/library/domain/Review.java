package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "isbn_no", nullable = false)
    private String isbn_no;

    @Column(name = "student_number", nullable = false)
    private String student_number;

    @Column(name = "score", nullable = false)
    private String score;

    @Builder
    public Review(String title, String content, String isbn_no, String student_number, String score) {
        this.title = title;
        this.content = content;
        this.isbn_no = isbn_no;
        this.student_number = student_number;
        this.score = score;
    }

    public void update(String title, String content, String isbn_no, String student_number, String score) {
        this.title = title;
        this.content = content;
        this.isbn_no = isbn_no;
        this.student_number = student_number;
        this.score = score;
    }
}
