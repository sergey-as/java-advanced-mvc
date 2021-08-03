package com.oktenweb.javaadvancedmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// цей клас створюється для того, щоб його як RestTemplate з методами, можна було передавати в інші класи, які
// будуть витягувати дані з інших сервісів
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate directorRestTemplate() {
//        return new RestTemplate();
//    }
}