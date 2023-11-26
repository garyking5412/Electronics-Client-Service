package com.example.electronicsspringbootclientservice.model;


import javax.persistence.*;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int ProductId;
    @Column(name = "name")
    private String ProductName;
    @Column(name = "detail")
    private String ProductDetail;
    @Column(name = "price")
    private Long ProductPrice;
    @Column(name = "image")
    private String ProductImage;
    @Column(name = "createdDate")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "cateId")
    private Category category;

    public Product(ProductDTO dto) {
        ProductName = dto.getProductName();
        ProductDetail = dto.getProductDetail();
        ProductPrice = dto.getProductPrice();
        ProductImage = dto.getProductImage();
        createdDate = dto.getCreatedDate();
    }
}
