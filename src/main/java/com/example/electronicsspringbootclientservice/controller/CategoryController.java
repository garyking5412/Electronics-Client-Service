package com.example.electronicsspringbootclientservice.controller;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> result = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable(name = "id") Integer id) {
        try {
            CategoryDTO result = categoryService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<CategoryDTO> insert(@RequestBody @Valid CategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.insert(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        if (categoryService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deleted Failed!");
    }
}
