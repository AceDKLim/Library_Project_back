package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.domain.Book;
import com.library.library.dto.AddBookRequest;
import com.library.library.dto.UpdateBookRequest;
import com.library.library.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book save(AddBookRequest request) {
        return bookRepository.save(request.toBook());
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByIsbn_no(String isbn_no) {
        return bookRepository.findById(isbn_no).orElseThrow(() -> new IllegalArgumentException("not found: " + isbn_no));
    }

    public void delete(String isbn_no) {
        bookRepository.deleteById(isbn_no);
    }

    @Transactional
    public Book update(String isbn_no, UpdateBookRequest request) {
        Book book = bookRepository.findById(isbn_no)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + isbn_no));
        book.update(isbn_no, request.getTitle(), request.getAuthor(), request.getPublish(), request.getP_year(), request.getNum(), request.getLocation(), request.getImage_src(), request.getDetail_src(), request.getTags());
        return book;
    }
}
