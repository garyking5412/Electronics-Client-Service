package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Integer prodId);
    ProductDTO insert(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO);
    void delete(Integer prodId);
}
