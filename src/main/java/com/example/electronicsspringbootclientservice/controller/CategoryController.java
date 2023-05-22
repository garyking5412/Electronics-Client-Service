package com.example.electronicsspringbootclientservice.controller;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
        @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> result = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
