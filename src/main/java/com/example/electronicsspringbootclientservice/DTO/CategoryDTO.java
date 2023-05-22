package com.example.electronicsspringbootclientservice.DTO;

import com.example.electronicsspringbootclientservice.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private int CategoryId;
    private String CategoryName;
    private String CategoryDetail;
    private List<Product> productList;
}
