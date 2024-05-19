package com.library.library.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.library.library.domain.PopularBook;
import com.library.library.dto.PopularBookResponse;
import com.library.library.repository.PopularBookRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PopularBookService {

    private final PopularBookRepository popularBookRepository;

    public List<PopularBookResponse> show() {
        return popularBookRepository.findAllByCountDesc().stream().map(PopularBookResponse::new).toList();
    }

    @Transactional
    public PopularBook click(String isbn_no) {
        PopularBook popularBook;
        try {
            popularBook = popularBookRepository.findById(isbn_no).get();
        } catch (Exception e) {
            popularBookRepository.save(PopularBook.builder().isbn_no(isbn_no).count(0).build());
            popularBook = popularBookRepository.findById(isbn_no).get();
        }
        popularBook.update(isbn_no);
        return popularBook;
    }

    @Transactional
    @Async
    @Scheduled(cron = "0 0 0 MON")
    public void autoDelete() {
        popularBookRepository.deleteAll();
    }
}
