package com.library.library.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.service.BestSellerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BestSellerController {

    private final BestSellerService bestSellerService;

    @GetMapping("api/bestseller")
    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        return ResponseEntity.ok().body(bestSellerService.fetch().getBody());
    }

}
