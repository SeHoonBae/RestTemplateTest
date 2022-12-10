package com.example.resttemplate.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    private ClientHttpRequestFactory getClientHttpRequestFactory(){
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout, TimeUnit.MILLISECONDS)
                .setConnectionKeepAlive(TimeValue.of(timeout, TimeUnit.MILLISECONDS))
                .build();

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        return restTemplate;
    }

}
