package com.example.electronicsspringbootclientservice.DTO;

import com.example.electronicsspringbootclientservice.model.Product;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private String detail;
}
