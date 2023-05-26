package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.gRPCService.PingService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Configuration
@EnableAsync
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


}
