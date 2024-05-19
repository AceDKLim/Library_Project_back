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
public class PopularBook {

    @Id
    @Column(name = "isbnNo", nullable = false)
    private String isbnNo;

    @Column(name = "count", nullable = false)
    private int count;

    @Builder
    public PopularBook(String isbnNo, int count) {
        this.isbnNo = isbnNo;
        this.count = count;
    }

    public void update(String isbnNo) {
        this.isbnNo = isbnNo;
        this.count = this.count + 1;
    }
}
