package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.model.Product;
import com.example.electronicsspringbootclientservice.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelUtils excelUtils;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public void uploadExcel(MultipartFile file, Class cls) {
        Workbook workbook = excelUtils.initWorkbook(file, file.getOriginalFilename());
        if (cls.isInstance(new ProductDTO())) {
            List<ProductDTO> dataList;
            try {
                dataList = excelUtils.parseExcelToDto(ProductDTO.class, workbook.getSheetAt(0));
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            productService.saveAll(dataList);
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response, Class cls) {
        if (cls.isInstance(new ProductDTO())) {
            List<ProductDTO> dataList = productService.getAllProducts();
            try {
                excelUtils.exportExcel(response, dataList, ProductDTO.class);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
