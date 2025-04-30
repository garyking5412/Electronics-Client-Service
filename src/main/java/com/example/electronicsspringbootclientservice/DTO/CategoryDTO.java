package com.example.electronicsspringbootclientservice.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CategoryDTO {
    private String id;
    @NotEmpty
    private String name;
    private String detail;
}
