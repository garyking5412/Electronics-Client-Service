package com.example.electronicsspringbootclientservice.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public Gson gsonTemplate() {
        return new Gson();
    }
}
