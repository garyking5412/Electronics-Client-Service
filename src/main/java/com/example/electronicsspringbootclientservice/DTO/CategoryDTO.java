package com.example.electronicsspringbootclientservice.DTO;

import com.example.electronicsspringbootclientservice.model.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    @NotEmpty
    private String name;
    private String detail;
}
