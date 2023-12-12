package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.service.CategoryServiceImpl;
import com.example.electronicsspringbootclientservice.service.ProductService;
import com.example.electronicsspringbootclientservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

@SpringBootTest
class ElectronicsSpringBootClientServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCategoryServiceBean() {
        Assertions.assertTrue(context.getBean(CategoryServiceImpl.class) != null);
    }

    @Test
    public void testProductServiceInsert() {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(01);
        productDTO.setProductName("dtoTest");
        productDTO.setProductPrice((long) 12.0);
        Assertions.assertEquals(null, productService.insert(productDTO));
    }
}
