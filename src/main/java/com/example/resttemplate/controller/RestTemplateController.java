package com.example.resttemplate.controller;

import com.example.resttemplate.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/resttemplate/object/test")
    public void getForObject(@RequestParam String name, @RequestParam String id){
        restTemplateService.getForObject(name, id);
    }

    @GetMapping("/resttemplate/entity/test")
    public void getForEntity(@RequestParam String name, @RequestParam String id){
        restTemplateService.getForEntity(name, id);
    }

    @PostMapping("/resttemplate/object/test")
    public void postForObject(@RequestParam String name, @RequestParam String id){
        restTemplateService.postForObject(name, id);
    }

    @PostMapping("/resttemplate/entity/test")
    public void postForEntity(@RequestParam String name, @RequestParam String id){
        restTemplateService.postForEntity(name, id);
    }

    @PostMapping("/resttemplate/exchange/test")
    public void postExchange(@RequestParam String name, @RequestParam String id){
        restTemplateService.postExchange(name, id);
    }



}
