package com.example.electronicsspringbootclientservice.controller;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.gRPCService.CategoryGRPCClientImpl;
import com.example.electronicsspringbootclientservice.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;
//    private final WebSocketService webSocketService;
    private final CategoryGRPCClientImpl categoryGRPCService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> result = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/web-socket/create")
    public void createViaWebSocket() throws JsonProcessingException {
//        webSocketService.createCategory();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
    }

//    @GetMapping("/getById/grpc/{id}")
//    public ResponseEntity<Category> getByIdGRpc(@PathVariable(name = "id") Integer id) {
//        CategoryRequest request = CategoryRequest.newBuilder().setId(id).build();
//        CategoryResponse response = categoryGRPCService.getCategory(request);
//        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(response, Category.class));
//    }

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
