package com.example.electronicsspringbootclientservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="CategoryId")
    private int CategoryId;
    @Column(name = "CategoryName")
    private String CategoryName;
    @Column(name = "CategoryDetail")
    private String CategoryDetail;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> productList;
}
