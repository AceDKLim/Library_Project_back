package com.library.library.dto;

import com.library.library.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddBookRequest {

    private String isbn_no;
    private String title;
    private String author;
    private String publish;
    private String p_year;
    private String num;
    private String location;
    private String image_src;
    private String detail_src;
    private String tags;

    public Book toBook() {
        return Book.builder().isbn_no(isbn_no).title(title).author(author).publish(publish).p_year(p_year).num(num)
                .location(location).image_src(image_src).detail_src(detail_src).tags(tags).build();
    }

}
