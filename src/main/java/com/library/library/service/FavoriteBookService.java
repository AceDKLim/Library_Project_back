package com.library.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library.domain.FavoriteBook;
import com.library.library.dto.AddFavoriteBookRequest;
import com.library.library.repository.FavoriteBookRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavoriteBookService {

    private final FavoriteBookRepository favoriteBookRepository;

    public FavoriteBook save(AddFavoriteBookRequest request) {
        return favoriteBookRepository.save(request.toFavoriteBook());
    }

    public List<FavoriteBook> showMyFavorite(String studentID) {
        return favoriteBookRepository.findAllByStudentID(studentID);
    }

    public Boolean showBookFavorite(String isbnNo, String studentID) {
        List<FavoriteBook> favoriteBook = favoriteBookRepository.findByStudentIDAndIsbnNo(studentID, isbnNo);
        if (favoriteBook.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public void delete(String isbnNo, String studentID) {
        favoriteBookRepository.deleteByIsbnNoAndStudentID(isbnNo, studentID);
    }

}
