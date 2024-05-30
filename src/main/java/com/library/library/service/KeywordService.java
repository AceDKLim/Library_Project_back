package com.library.library.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.library.domain.Keyword;
import com.library.library.domain.User;
import com.library.library.dto.AddKeywordRequest;
import com.library.library.dto.UpdateKeywordRequest;
import com.library.library.repository.KeywordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public Keyword saveKeyword(AddKeywordRequest request) {
        return keywordRepository.save(request.toKeyword());
    }

    public Keyword findKeywords() {
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return keywordRepository.findByStudentID(login_user.getStudentID());
    }

    @Transactional
    public Keyword update(UpdateKeywordRequest tags) {
        User login_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Keyword keyword = keywordRepository.findByStudentID(login_user.getStudentID());
        keyword.update(login_user.getStudentID(), tags.getTags());
        return keyword;
    }
}
