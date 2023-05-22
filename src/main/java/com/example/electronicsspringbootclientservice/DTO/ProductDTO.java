package com.example.electronicsspringbootclientservice.DTO;

import com.example.electronicsspringbootclientservice.model.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductDTO {
    private int ProductId;
    private String ProductName;
    private String ProductDetail;
    private Long ProductPrice;
    private String ProductImage;
    private Category category;
}
