package com.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateBookRequest {

    // private String isbn_no;
    private String title;
    private String author;
    private String publish;
    private String p_year;
    private String num;
    private String location;
    private String image_src;
    private String detail_src;
    private String tags;

}
