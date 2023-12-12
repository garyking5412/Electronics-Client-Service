package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import org.springframework.data.crossstore.ChangeSetPersister;


import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Integer cateId) throws ChangeSetPersister.NotFoundException;

    CategoryDTO insert(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    boolean delete(Integer cateId);
}
