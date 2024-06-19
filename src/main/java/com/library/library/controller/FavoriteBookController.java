package com.library.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.domain.FavoriteBook;
import com.library.library.domain.User;
import com.library.library.dto.AddFavoriteBookRequest;
import com.library.library.service.BookService;
import com.library.library.service.FavoriteBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FavoriteBookController {

    private final BookService bookService;
    private final FavoriteBookService favoriteBookService;

    @PostMapping("/api/favoritebook")
    public ResponseEntity<FavoriteBook> addFavoriteBooks(@RequestBody AddFavoriteBookRequest request) {
        FavoriteBook savedFavoriteBook = favoriteBookService.save(request);
        return ResponseEntity.ok().body(savedFavoriteBook);
    }

    @GetMapping("/api/favoritebook")
    public ResponseEntity<List<Book>> findMyFavoriteBooks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FavoriteBook> favorite = favoriteBookService.showMyFavorite(user.getStudentID());
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < favorite.size(); i++) {
            String isbnNo = favorite.get(i).getIsbnNo();
            Book book = bookService.findByIsbnNo(isbnNo);
            books.add(book);
        }

        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/api/favoritebook/{isbnNo}")
    public ResponseEntity<Boolean> findBookFavoriteBooks(@PathVariable String isbnNo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isFavorite = favoriteBookService.showBookFavorite(isbnNo, user.getStudentID());
        return ResponseEntity.ok().body(isFavorite);
    }

    @DeleteMapping("/api/favoritebook/{isbnNo}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbnNo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favoriteBookService.delete(isbnNo, user.getStudentID());
        return ResponseEntity.ok().build();
    }
}
