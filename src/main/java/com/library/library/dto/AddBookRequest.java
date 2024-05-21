package com.library.library.dto;

import com.library.library.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBookRequest {

    private String isbnNo;
    private String title;
    private String author;
    private String publish;
    private String pubyear;
    private String num;
    private String location;
    private String imageSrc;
    private String detailSrc;
    private String tags;

    public Book toBook() {
        return Book.builder().isbnNo(isbnNo).title(title).author(author).publish(publish).pubyear(pubyear).num(num)
                .location(location).imageSrc(imageSrc).detailSrc(detailSrc).tags(tags).build();
    }

}
