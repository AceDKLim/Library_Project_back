package com.library.library.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.library.service.AladinApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
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
