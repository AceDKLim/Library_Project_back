package com.library.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.domain.Keyword;
import com.library.library.dto.AddKeywordRequest;
import com.library.library.dto.AddRecommendRequest;
import com.library.library.dto.KeywordResponse;
import com.library.library.dto.UpdateKeywordRequest;
import com.library.library.service.KeywordService;
import com.library.library.service.RecommendBookService;
import com.library.library.service.RecommendedService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class KeywordApiController {

    private final KeywordService keywordService;
    private final RecommendBookService recommendBookService;
    private final RecommendedService recommendedService;

    @PostMapping("/api/keywords")
    public ResponseEntity<Keyword> addKeyword(@RequestBody AddKeywordRequest request) {
        Keyword keyword = keywordService.saveKeyword(request);
        AddRecommendRequest result = recommendedService.getIsbn(keyword);
        recommendBookService.save(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(keyword);
    }

    @GetMapping("/api/keywords")
    public ResponseEntity<KeywordResponse> findKeywords() {
        Keyword keyword = keywordService.findKeywords();
        return ResponseEntity.ok().body(new KeywordResponse(keyword));
    }

    @PutMapping("/api/keywords")
    public ResponseEntity<Keyword> updateKeyword(@RequestBody UpdateKeywordRequest request) {
        Keyword keyword = keywordService.update(request);
        return ResponseEntity.ok().body(keyword);
    }

}
