package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.model.Product;
import com.example.electronicsspringbootclientservice.repository.ProductRepository;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RedisService redisService;

    @Override
    public List<ProductDTO> getAllProducts() {
        return redisService.getAllProduct();
    }

    @Override
    public ProductDTO getProductById(Integer prodId) {
        ProductDTO dto = new ProductDTO();
        Optional<Product> product = productRepository.findById(prodId);
        product.ifPresent(prod -> {
            dto.setProductId(prod.getProductId());
            dto.setProductName(prod.getProductName());
            dto.setProductPrice(prod.getProductPrice());
            if (Objects.nonNull(prod.getCategory())) {
                dto.setCategoryId(prod.getCategory().getId());
            } else {
                dto.setCategoryId(null);
            }
        });
        return dto;
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

    @Override
    public void saveAll(List<ProductDTO> dtoList) {
        List<Product> products = new ArrayList<>();
        dtoList.forEach(data -> {
            Product product = new Product(data);
            products.add(product);
        });
        List<Product> savedProducts = productRepository.saveAll(products);
        savedProducts.stream().map(ProductDTO::new).forEach(dto -> {
            redisService.saveProduct(dto);
        });
    }

    @Override
    public boolean deleteAllProduct() {
        productRepository.deleteAll();
        return redisService.deleteAllProducts();
    }
}
