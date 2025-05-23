package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.model.Category;
import com.example.electronicsspringbootclientservice.model.Category2;
import com.example.electronicsspringbootclientservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    private final MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> result = Collections.synchronizedList(new ArrayList<>());
        categories.forEach(category -> {
            CategoryDTO dto = new CategoryDTO();
            modelMapper.map(category, dto);
            result.add(dto);
        });
        logger.info(">>>>>>>>>>>>>>>>>>>>>> GETTING ALL CATEGORIES >>>>>>>>>>>>>>>>>");
        return result;
    }

    @Override
    public CategoryDTO getCategoryById(Integer cateId) {
        Category category = categoryRepository.findById(cateId).orElse(null);
        CategoryDTO dto = new CategoryDTO();
        modelMapper.map(category, dto);
        return dto;
    }

    @Override
    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category2 category = new Category2();
        modelMapper.map(categoryDTO, category);
        Category2 savedCategory = mongoTemplate.insert(category);
        modelMapper.map(savedCategory, categoryDTO);
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category category = new Category();
        modelMapper.map(categoryDTO, category);
        Category result = categoryRepository.save(category);
        modelMapper.map(result, categoryDTO);
        return categoryDTO;
    }

    @Override
    public boolean delete(Integer cateId) {
        try {
            categoryRepository.deleteById(cateId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
