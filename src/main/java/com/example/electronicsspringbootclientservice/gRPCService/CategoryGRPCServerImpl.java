package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryGRPCServerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;


}
