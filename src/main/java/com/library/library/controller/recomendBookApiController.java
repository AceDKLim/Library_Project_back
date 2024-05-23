package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.domain.User;
import com.library.library.dto.BookResponse;
import com.library.library.service.BookService;
import com.library.library.service.RecommendBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class recomendBookApiController {

    private final BookService bookService;
    private final RecommendBookService recommendBookService;

    @GetMapping("/api/recommendbook")
    public ResponseEntity<List<BookResponse>> findRecommendBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String recommendBook = recommendBookService.findbyStudentID(user.getStudentID()).getIsbnNo();
        List<BookResponse> books = new ArrayList<>();
        String recommendBooks[] = recommendBook.split(",");
        int len = Math.min(recommendBooks.length, 10);
        for (int i = 0; i < len; i++) {
            Book book = bookService.findByIsbnNo(recommendBooks[i]);
            books.add(new BookResponse(book));
        }

        return ResponseEntity.ok().body(books);
    }
}
