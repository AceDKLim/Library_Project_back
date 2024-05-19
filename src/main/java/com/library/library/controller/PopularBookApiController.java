package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.dto.BookResponse;
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
    public ResponseEntity<List<BookResponse>> findPopularBooks() {
        List<PopularBookResponse> popular = popularBookService.show();
        List<BookResponse> books = new ArrayList<>();

        int len = Math.min(popular.size(), 10);
        for (int i = 0; i < len; i++) {
            String isbn_no = popular.get(i).getIsbn_no();
            Book book = bookService.findByIsbn_no(isbn_no);
            books.add(new BookResponse(book));
        }

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/api/books/{isbn_no}")
    public ResponseEntity<BookResponse> findBook(@PathVariable String isbn_no) {
        Book book = bookService.findByIsbn_no(isbn_no);
        popularBookService.click(isbn_no);
        return ResponseEntity.ok().body(new BookResponse(book));
    }
}
