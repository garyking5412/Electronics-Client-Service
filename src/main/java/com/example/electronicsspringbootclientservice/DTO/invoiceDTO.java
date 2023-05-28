package com.example.electronicsspringbootclientservice.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class invoiceDTO {
    private Date invoiceDate;
    private Long invoiceTotal;
    private List<invoiceDetailDTO> billDetailList;
}
