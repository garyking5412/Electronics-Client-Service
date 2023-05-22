package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<ProductDTO> getAllProducts() {

        return null;
    }

    @Override
    public ProductDTO getProductById(Integer prodId) {
        return null;
    }

    @Override
    public ProductDTO insert(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        return null;
    }

    @Override
    public void delete(Integer prodId) {

    }
}
