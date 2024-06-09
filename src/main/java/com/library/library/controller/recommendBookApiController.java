package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.domain.RecommendBook;
import com.library.library.domain.User;
import com.library.library.dto.AddRecommendRequest;
import com.library.library.service.BookService;
import com.library.library.service.RecommendBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class recommendBookApiController {

    private final BookService bookService;
    private final RecommendBookService recommendBookService;

    @PostMapping("/api/recommend")
    public ResponseEntity<RecommendBook> addRecommend(@RequestBody AddRecommendRequest request) {
        RecommendBook savedRecommend = recommendBookService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecommend);
    }

    @GetMapping("/api/recommend")
    public ResponseEntity<List<Book>> findRecommendBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String recommendBook = recommendBookService.findbyStudentID(user.getStudentID()).getIsbnNo();
        List<Book> books = new ArrayList<>();
        String recommendBooks[] = recommendBook.split(",");
        int len = Math.min(recommendBooks.length, 11);
        for (int i = 0; i < len; i++) {
            Book book = bookService.findByIsbnNo(recommendBooks[i]);
            books.add(book);
        }

        return ResponseEntity.ok().body(books);
    }
}
