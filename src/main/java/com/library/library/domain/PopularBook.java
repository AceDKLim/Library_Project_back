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
    @Column(name = "isbn_no", nullable = false)
    private String isbn_no;

    @Column(name = "title", nullable = false)
    private int count;

    @Builder
    public PopularBook(String isbn_no, int count) {
        this.isbn_no = isbn_no;
        this.count = count;
    }

    public void update(String isbn_no) {
        this.isbn_no = isbn_no;
        this.count = this.count + 1;
    }
}
