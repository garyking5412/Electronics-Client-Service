package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.service.ProductService;
import grpc.CategoryServiceGrpc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@GRpcService
@AllArgsConstructor
@Slf4j
public class ProductGRPCServiceImpl extends CategoryServiceGrpc.CategoryServiceImplBase {

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public boolean validateProductQueryOutput(ProductDTO dto) {
        return !Objects.isNull(dto.getProductId()) && !Objects.isNull(dto.getProductName()) && !Objects.isNull(dto.getProductPrice());
    }
}
