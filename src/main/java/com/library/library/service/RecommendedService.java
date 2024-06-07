package com.library.library.service;

import org.json.simple.JSONObject;
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

import com.library.library.domain.Keyword;

import java.util.HashMap;

@Service
public class RecommendedService {
    private final String Aiurl = "";

    @SuppressWarnings("unchecked")
    public String getIsbn(Keyword keyword) {
        HashMap<String, Object> result = new HashMap<>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            JSONObject requestBody = new JSONObject();
            requestBody.put("keyword", keyword.getTags().toString());
            HttpEntity<Object> entity = new HttpEntity<>(requestBody.toString(), header);
            UriComponents uri = UriComponentsBuilder
                    .fromHttpUrl(Aiurl)
                    .build();
            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);
            result.put("statusCode", resultMap.getStatusCode());
            result.put("header", resultMap.getHeaders());
            result.put("body", resultMap.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getStatusCode());
            result.put("body", e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());
            return resultMap.toString();
        } catch (RestClientException e) {
            result.put("statusCode", "999");
            result.put("body", "exception error");
            System.out.println(e.toString());
            return resultMap.toString();
        }
        return resultMap.getBody().toString();
    }
}
