package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;


import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Integer cateId);
    CategoryDTO insert(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    void delete(Integer cateId);
}
