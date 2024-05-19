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

    public Book findByIsbnNo(String isbnNo) {
        return bookRepository.findById(isbnNo)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + isbnNo));
    }

    public List<Book> SearchBook(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    public void delete(String isbnNo) {
        bookRepository.deleteById(isbnNo);
    }

    @Transactional
    public Book update(String isbnNo, UpdateBookRequest request) {
        Book book = bookRepository.findById(isbnNo)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + isbnNo));
        book.update(isbnNo, request.getTitle(), request.getAuthor(), request.getPublish(), request.getP_year(), request.getNum(), request.getLocation(), request.getImage_src(), request.getDetail_src(), request.getTags());
        return book;
    }
}
