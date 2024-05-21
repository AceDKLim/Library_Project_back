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

@Table(name = "popular")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "isbnNo", nullable = false)
    private String isbnNo;

    @Column(name = "studentID", nullable = false)
    private String studentID;

    @Builder
    public RecommendBook(String isbnNo, String studentID) {
        this.isbnNo = isbnNo;
        this.studentID = studentID;
    }

    public void update(String isbnNo, String studentID) {
        this.isbnNo = isbnNo;
        this.studentID = studentID;
    }
}
