package com.library.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Book;
import com.library.library.dto.AddBookRequest;
import com.library.library.dto.BookResponse;
import com.library.library.dto.UpdateBookRequest;
import com.library.library.service.BookService;
import com.library.library.service.PopularBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;
    private final PopularBookService popularBookService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/api/books")
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest request) {
        Book savedBook = bookService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<BookResponse> books = bookService.findAll().stream().map(BookResponse::new).toList();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/api/books/{isbn_no}")
    public ResponseEntity<BookResponse> findBook(@PathVariable String isbn_no) {
        Book book = bookService.findByIsbn_no(isbn_no);
        popularBookService.click(isbn_no);
        return ResponseEntity.ok().body(new BookResponse(book));
    }

    @GetMapping("/api/search/{key_word}")
    public ResponseEntity<List<BookResponse>> SearchBook(@PathVariable String key_word) {
        List<BookResponse> books = bookService.SearchBook(key_word).stream().map(BookResponse::new).toList();
        return ResponseEntity.ok().body(books);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/api/books/{isbn_no}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn_no) {
        bookService.delete(isbn_no);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/api/books/{isbn_no}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn_no, @RequestBody UpdateBookRequest request) {
        Book updatBook = bookService.update(isbn_no, request);
        return ResponseEntity.ok().body(updatBook);
    }
}
