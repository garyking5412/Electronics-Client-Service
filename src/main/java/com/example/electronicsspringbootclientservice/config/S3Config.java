package com.example.electronicsspringbootclientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.s3.S3Client;

//@Configuration
public class S3Config {

//    @Bean
    public static S3Client s3ClientBuilder() {
        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .build();
    }
}
