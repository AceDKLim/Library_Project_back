package com.library.library.controller;

import java.io.UnsupportedEncodingException;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.service.AladinApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @Slf4j
public class BestSellerController {

    private final AladinApiService aladinApiService;

    @GetMapping("api/bestseller")
    public ResponseEntity<?> Best() throws UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok().body(aladinApiService.BestSeller());
    }

    @GetMapping("api/newbook")
    public ResponseEntity<?> New() throws UnsupportedEncodingException, ParseException {
        return ResponseEntity.ok().body(aladinApiService.NewBook());
    }
}
