package com.library.library.service;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AladinApiService {

    private final String BASE_URL = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private final String ttbkey = "?ttbkey=ttbeleven10001124001";
    private final String QueryType = "&QueryType=Bestseller";
    private final String SearchTarget = "&SearchTarget=Book";
    private final String MaxResults = "&MaxResults=10";
    private final String start = "&start=1";
    private final String output = "&output=js";
    private final String Version = "&Version=20131101";

    public ResponseEntity<Object> BestSeller() {

        HashMap<String, Object> result = new HashMap<>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            UriComponents uri = UriComponentsBuilder
                    .fromHttpUrl(BASE_URL + ttbkey + QueryType + SearchTarget + MaxResults + start + output + Version)
                    .build();
            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);
            result.put("statusCode", resultMap.getStatusCode());
            result.put("header", resultMap.getHeaders());
            result.put("body", resultMap.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getStatusCode());
            result.put("body", e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());
            return resultMap;
        } catch (RestClientException e) {
            result.put("statusCode", "999");
            result.put("body", "exception error");
            System.out.println(e.toString());
            return resultMap;
        }
        return resultMap;
    }
}
