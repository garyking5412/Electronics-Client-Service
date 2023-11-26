package com.example.electronicsspringbootclientservice.DTO;

import com.example.electronicsspringbootclientservice.model.Category;

import javax.persistence.*;

import com.example.electronicsspringbootclientservice.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Integer ProductId;
    private String ProductName;
    private String ProductDetail;
    private Long ProductPrice;
    private String ProductImage;
    private Date createdDate;
    private Integer categoryId;

    public ProductDTO(Product product) {
        BeanUtils.copyProperties(product, this);
//        this.categoryId = product.getCategory().getId();
    }
}
