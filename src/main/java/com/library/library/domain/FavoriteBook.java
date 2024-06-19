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

@Table(name = "favorite")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "studentID", nullable = false)
    private String studentID;

    @Column(name = "isbnNo", nullable = false)
    private String isbnNo;

    @Builder
    public FavoriteBook(String studentID, String isbnNo) {
        this.studentID = studentID;
        this.isbnNo = isbnNo;
    }
}
