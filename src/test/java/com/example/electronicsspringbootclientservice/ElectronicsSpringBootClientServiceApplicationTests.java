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

@SpringBootTest
class ElectronicsSpringBootClientServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

//    @Test
//    void contextLoads() {
//        // TODO document why this method is empty
//    }

//    @Test
//    void testCategoryServiceBean() {
//        context.getBean(CategoryServiceImpl.class);
//        Assertions.assertTrue(true);
//    }
//
//    @Test
//    void testProductServiceInsert() {
//        ProductService productService = context.getBean(ProductServiceImpl.class);
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setProductId(1);
//        productDTO.setProductName("dtoTest");
//        productDTO.setProductPrice((long) 12.0);
//        Assertions.assertNotEquals(null, productService.insert(productDTO));
//    }
}
