package com.example.electronicsspringbootclientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "category")
public class Category2 {
    @Id
    private int id;
    private String name;
    private String detail;
    private List<Product> productList;
}
