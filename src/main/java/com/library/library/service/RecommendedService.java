package com.library.library.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.library.domain.Keyword;

@Service
public class RecommendedService {

    @Autowired
    private RestTemplate restTemplate;

    public String getIsbn(Keyword keyword) {
        String Aiurl = "http://52.78.146.166:8000/keyword_to_isbn";
        HttpHeaders header = new HttpHeaders();

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("keyword", keyword.getTags());
        HttpEntity<Object> request = new HttpEntity<>(requestBody, header);
        ResponseEntity<String> response = restTemplate.exchange(Aiurl, HttpMethod.POST, request, String.class);
        return response.getBody();
    }
}
