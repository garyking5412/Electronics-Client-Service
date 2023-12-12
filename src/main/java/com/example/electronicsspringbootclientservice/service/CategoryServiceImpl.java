package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.model.Category;
import com.example.electronicsspringbootclientservice.repository.CategoryRepository;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public CategoryDTO getCategoryById(Integer cateId) throws ChangeSetPersister.NotFoundException {
        Category category = categoryRepository.findById(cateId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        CategoryDTO dto = new CategoryDTO();
        modelMapper.map(category, dto);
        return dto;
    }

    @Override
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        modelMapper.map(categoryDTO, category);
        Category result = categoryRepository.save(category);
        modelMapper.map(result, categoryDTO);
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
