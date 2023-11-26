package com.example.electronicsspringbootclientservice.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    void uploadExcel(@RequestBody MultipartFile file, Class cls);

    void downloadExcel(HttpServletResponse response, Class cls);
}
