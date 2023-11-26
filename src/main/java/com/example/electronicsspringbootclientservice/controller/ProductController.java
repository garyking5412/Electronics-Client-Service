package com.example.electronicsspringbootclientservice.controller;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.service.CategoryService;
import com.example.electronicsspringbootclientservice.service.ExcelService;
import com.example.electronicsspringbootclientservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ExcelService excelService;

    @PostMapping("/uploadExcelProduct")
    public void uploadExcelProduct(@RequestBody MultipartFile file) {
        excelService.uploadExcel(file, ProductDTO.class);
    }

    @GetMapping("/downloadExcelProduct")
    public void downloadExcelProduct(HttpServletResponse response) {
        excelService.downloadExcel(response, ProductDTO.class);
    }

    @DeleteMapping("/deleteAllProduct")
    public ResponseEntity<String> deleteAllProduct() {
        if (productService.deleteAllProduct()) {
            return ResponseEntity.status(HttpStatus.OK).body("DELETE SUCCESSFUL!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR DELETION!");
    }
}
