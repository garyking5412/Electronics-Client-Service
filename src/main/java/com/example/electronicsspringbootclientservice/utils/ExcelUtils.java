package com.example.electronicsspringbootclientservice.utils;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.TransactionException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelUtils {

    private final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    public Workbook initWorkbook(MultipartFile file, String fileName) {
        Workbook workbook = null;
        try {
            if (fileName.endsWith("xlsx")) {
                log.info("Start create XSSFWorkbook: {}", fileName);
                OPCPackage pkg = OPCPackage.open(file.getInputStream());
                workbook = new XSSFWorkbook(pkg);
                log.info("Finish create XSSFWorkbook: {}", fileName);
            } else {
                log.info("Start create HSSFWorkbook: {}", fileName);
                workbook = new HSSFWorkbook(file.getInputStream());
                log.info("Finish create HSSFWorkbook: {}", fileName);
            }
        } catch (Exception e) {
            log.error("Exception: ", e);
            throw new TransactionException("exception.excel.read.excel.file.fail");
        }
        return workbook;
    }

    public void getFieldsOfAClass(Class cls, List<Field> fieldList) {
        if (cls.getDeclaredFields().length > 0) {
            Field[] fields = cls.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
        }

        if (cls.getSuperclass() != null && !cls.getSuperclass().equals(Object.class)) {
            getFieldsOfAClass(cls.getSuperclass(), fieldList);
        }
    }

    private boolean checkIfRowIsEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }

        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
                return false;
            }
        }
        return true;
    }

    public <T> List<T> parseExcelToDto(Class<T> cls, Sheet sheet) throws TransactionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> dataList = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
//        Map<String, Field> fieldByFieldName = new HashMap<>();
        getFieldsOfAClass(cls, fieldList);
        fieldList.forEach(field -> {
            field.setAccessible(true);
//            fieldByFieldName.put(field.getName(), field);
        });
        for (int rowIndex = sheet.getFirstRowNum() + 1; rowIndex <= Constants.MAX_EXCEL_ROW; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (checkIfRowIsEmpty(row)) {
                continue;
            }
            T item = cls.getDeclaredConstructor().newInstance();
            ProductDTO dto = new ProductDTO();
            if (item.getClass().isInstance(dto)) {
                item = (T) dto;
                ((ProductDTO) item).setProductName(row.getCell(0).getStringCellValue());
                ((ProductDTO) item).setProductDetail(row.getCell(1).getStringCellValue());
                ((ProductDTO) item).setProductPrice((long) row.getCell(2).getNumericCellValue());
                ((ProductDTO) item).setProductImage(row.getCell(3).getStringCellValue());
                ((ProductDTO) item).setCreatedDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                ((ProductDTO) item).setCategoryId((int) row.getCell(4).getNumericCellValue());
                dataList.add(item);
            }
        }
        return dataList;
    }

    public void buildExcelDocument(String fileName, SXSSFWorkbook wb, HttpServletResponse response) {
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.flushBuffer();
            wb.write(response.getOutputStream());
            wb.dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void exportExcel(HttpServletResponse response, List<T> dataList, Class<T> cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T item = cls.getDeclaredConstructor().newInstance();
        if (item.getClass().isInstance(new ProductDTO())) {
            SXSSFWorkbook wb = new SXSSFWorkbook(Constants.MAX_EXCEL_ROW);
            Sheet sheet = wb.createSheet("bankai");
            AtomicInteger rowNumber = new AtomicInteger();
            AtomicInteger cellNumber = new AtomicInteger();
            Row row = sheet.createRow(0);
            List<Field> fieldList = new ArrayList<>();
            getFieldsOfAClass(cls, fieldList);
            fieldList.forEach(field -> {
                field.setAccessible(true);
                Cell cell = row.createCell(cellNumber.get());
                cell.setCellValue(field.getName());
                cellNumber.getAndIncrement();
            });
            dataList.forEach(data -> {
                rowNumber.getAndIncrement();
                Row dataRow = sheet.createRow(rowNumber.get());
                AtomicInteger dataCellNumber = new AtomicInteger();
                fieldList.forEach(field -> {
                    Class<?> fieldType = field.getType();
                    Object value = StringUtils.EMPTY;
                    try {
                        value = field.get(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Cell dataCell = dataRow.createCell(dataCellNumber.get());
                    if (value != null) {
                        if (fieldType == Date.class) {
                            dataCell.setCellValue(value.toString());
                        } else {
                            dataCell.setCellValue(value.toString().replaceAll("\\|", ","));
                        }
                    }
                    dataCellNumber.getAndIncrement();
                });
            });
            buildExcelDocument("MINAZUKI.xlsx", wb, response);
        }
    }

}
