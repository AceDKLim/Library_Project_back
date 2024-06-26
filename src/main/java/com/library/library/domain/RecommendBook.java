package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "recommend")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendBook {

    @Id
    @Column(name = "studentID", nullable = false)
    private String studentID;

    @Column(name = "isbnNo", nullable = false)
    private String isbnNo;

    @Builder
    public RecommendBook(String studentID, String isbnNo) {
        this.studentID = studentID;
        this.isbnNo = isbnNo;
    }

    public void update(String isbnNo) {
        this.isbnNo = isbnNo;
    }
}
