package com.example.electronicsspringbootclientservice.DTO;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String to;
    private String toName;
    private String content;
}
