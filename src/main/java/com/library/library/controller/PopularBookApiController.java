package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.dto.PopularBookResponse;
import com.library.library.service.BookService;
import com.library.library.service.PopularBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PopularBookApiController {

    private final BookService bookService;
    private final PopularBookService popularBookService;

    @GetMapping("/api/popularbooks")
    public ResponseEntity<List<Book>> findPopularBooks() {
        List<PopularBookResponse> popular = popularBookService.show();
        List<Book> books = new ArrayList<>();

        int len = Math.min(popular.size(), 10);
        for (int i = 0; i < len; i++) {
            String isbnNo = popular.get(i).getIsbnNo();
            Book book = bookService.findByIsbnNo(isbnNo);
            books.add(book);
        }

        return ResponseEntity.ok().body(books);
    }
}
