package com.library.library.service;

import java.io.UnsupportedEncodingException;

import org.hibernate.mapping.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BestSellerService {

    private final String BASE_URL = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private final String ttbkey = "?ttbkey=ttbeleven10001124001";
    private final String QueryType = "&QueryType=Bestseller";
    private final String SearchTarget = "&SearchTarget=Book";
    private final String MaxResults = "&MaxResults=10";
    private final String start = "&start=1";
    private final String output = "&output=js";
    private final String Version = "&Version=20131101";

    private String makeUrl() throws UnsupportedEncodingException {
        return BASE_URL + ttbkey + QueryType + SearchTarget + MaxResults + start + output + Version;
    }

    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;
    }
}
