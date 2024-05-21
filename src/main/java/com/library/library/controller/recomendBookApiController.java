package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.dto.BookResponse;
import com.library.library.dto.RecommendBookResponse;
import com.library.library.service.BookService;
import com.library.library.service.RecommendBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class recomendBookApiController {

    private final BookService bookService;
    private final RecommendBookService recommendBookService;

    @GetMapping("/api/recommendbook/{studentID}")
    public ResponseEntity<List<BookResponse>> findPopularBooks(@PathVariable String studentID) {
        List<RecommendBookResponse> recommend = recommendBookService.findbyStudentID(studentID);
        List<BookResponse> books = new ArrayList<>();

        int len = Math.min(recommend.size(), 10);
        for (int i = 0; i < len; i++) {
            String isbnNo = recommend.get(i).getIsbnNo();
            Book book = bookService.findByIsbnNo(isbnNo);
            books.add(new BookResponse(book));
        }

        return ResponseEntity.ok().body(books);
    }
}
