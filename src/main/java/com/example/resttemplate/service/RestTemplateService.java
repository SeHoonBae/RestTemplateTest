package com.example.resttemplate.service;

import com.example.resttemplate.dto.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public void getForObject(String name, String id) {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/mockmvc")
                .queryParam("name", name)
                .queryParam("id", id)
                .encode()
                .build()
                .toUri();

        log.info("uri: {}", uri);

        String result = restTemplate.getForObject(uri, String.class);

        log.info("result: {}", result);
    }

    public void getForEntity(String name, String id) {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/mockmvc")
                .queryParam("name", name)
                .queryParam("id", id)
                .encode()
                .build()
                .toUri();

        log.info("uri: {}", uri);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        log.info("result.getStatusCode: {}", result.getStatusCode());
        log.info("result.getBody: {}", result.getBody());
        log.info("result.getHeaders: {}", result.getHeaders());
    }

    public void postForObject(String name, String id) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/mockmvc")
                .encode()
                .build()
                .toUri();

        log.info("uri : {}", uri);

        Info info = new Info();
        info.setName(name);
        info.setId(id);

        String result = restTemplate.postForObject(uri, info, String.class);
        log.info("result: {}", result);
    }

    public void postForEntity(String name, String id) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/mockmvc")
                .encode()
                .build()
                .toUri();
        
        log.info("uri: {}", uri);

        Info info = new Info();
        info.setName(name);
        info.setId(id);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, info, String.class);

        log.info("result.getStatusCode: {}", result.getStatusCode());
        log.info("result.getBody: {}", result.getBody());
        log.info("result.getHeaders: {}", result.getHeaders());
    }

    public void postExchange(String name, String id) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/mockmvc")
                .encode()
                .build()
                .toUri();

        log.info("uri: {}", uri);

        Info info = new Info();
        info.setName(name);
        info.setId(id);

        RequestEntity<Info> req = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "my-header")
                .body(info);

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        log.info("result.getStatusCode: {}", result.getStatusCode());
        log.info("result.getBody: {}", result.getBody());
        log.info("result.getHeaders: {}", result.getHeaders());

    }
}
