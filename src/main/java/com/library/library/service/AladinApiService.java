package com.library.library.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AladinApiService {

    private final String BASE_URL = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private final String ttbkey = "?ttbkey=ttbeleven10001124001";
    private final String bestQueryType = "&QueryType=Bestseller";
    private final String newQueryType = "&QueryType=ItemNewSpecial";
    private final String SearchTarget = "&SearchTarget=Book";
    private final String MaxResults = "&MaxResults=10";
    private final String start = "&start=1";
    private final String output = "&output=js";
    private final String Version = "&Version=20131101";

    private final String bestseller = BASE_URL + ttbkey + bestQueryType + SearchTarget + MaxResults + start + output
            + Version;
    private final String newbook = BASE_URL + ttbkey + newQueryType + SearchTarget + MaxResults + start + output
            + Version;

    public ResponseEntity<Object> getBook(String url) {
        HashMap<String, Object> result = new HashMap<>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            UriComponents uri = UriComponentsBuilder
                    .fromHttpUrl(url)
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

    public ArrayList<HashMap<String, String>> fetch(String url) throws UnsupportedEncodingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        String books;
        try {
            books = objectMapper.writeValueAsString(getBook(url).getBody());
        } catch (JsonProcessingException e) {
            return result;
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(books);
        JSONArray items = (JSONArray) jsonObject.get("item");
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> info = new HashMap<>();
            JSONObject book = (JSONObject) items.get(i);
            info.put("title", book.get("title").toString());
            info.put("link", book.get("link").toString());
            info.put("cover", book.get("cover").toString());
            result.add(info);
        }
        return result;
    }

    public ArrayList<HashMap<String, String>> BestSeller() throws UnsupportedEncodingException, ParseException {
        return fetch(bestseller);
    }

    public ArrayList<HashMap<String, String>> NewBook() throws UnsupportedEncodingException, ParseException {
        return fetch(newbook);
    }
}
