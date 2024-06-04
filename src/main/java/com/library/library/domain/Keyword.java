package com.library.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "keyword")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {

    @Id
    @Column(name = "studentID", nullable = false)
    private String studentID;

    @Column(name = "tags", nullable = false)
    private String tags;

    @Builder
    public Keyword(String studentID, String tags) {
        this.studentID = studentID;
        this.tags = tags;
    }

    public void update(String studentID, String tags) {
        this.tags = this.tags + "," + tags;
    }
}
