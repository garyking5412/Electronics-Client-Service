package com.example.electronicsspringbootclientservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Configuration
@EnableAsync
@EnableSwagger2
@EnableRabbit
public class ElectronicsSpringBootClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicsSpringBootClientServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

//    @Bean
//    NewTopic invoiceRequest() {
//        return new NewTopic("invoice", 2, (short) 1);
//    }
//
//    @Bean
//    NewTopic message() {
//        return new NewTopic("notification", 3, (short) 1);
//    }

}
