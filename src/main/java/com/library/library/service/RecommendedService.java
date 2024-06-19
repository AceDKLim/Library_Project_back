package com.library.library.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.library.domain.Keyword;

@Service
public class RecommendedService {

    private final RestTemplate restTemplate;

    @Value("${API-Key.aiKey}")
    private String Aiurl;

    public RecommendedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getIsbn(Keyword keyword) {
        HttpHeaders header = new HttpHeaders();
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("keyword", keyword.getTags());
        HttpEntity<Object> request = new HttpEntity<>(requestBody, header);
        ResponseEntity<Object> response = restTemplate.exchange(Aiurl, HttpMethod.POST, request, Object.class);
        return response.getBody().toString();
    }
}
