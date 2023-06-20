package com.example.electronicsspringbootclientservice.model;


import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="ProductId")
    private int ProductId;
    @Column(name = "ProductName")
    private String ProductName;
    @Column(name = "ProductDetail")
    private String ProductDetail;
    @Column(name = "ProductPrice")
    private Long ProductPrice;
    @Column(name = "ProductImage")
    private String ProductImage;
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}
