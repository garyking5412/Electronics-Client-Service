package com.example.electronicsspringbootclientservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Data
public class Category2 {
    @Id
    private String id;
    private String name;
    private String detail;
}
