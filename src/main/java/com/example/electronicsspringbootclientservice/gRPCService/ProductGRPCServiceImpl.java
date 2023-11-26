package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.*;
import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
//import com.example.electronicsspringbootclientservice.StudentServiceGrpc;
import com.example.electronicsspringbootclientservice.service.ProductService;
import com.example.electronicsspringbootclientservice.utils.Constants;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

@GRpcService
public class ProductGRPCServiceImpl extends PingServiceGrpc.PingServiceImplBase {

    @Autowired
    private ProductService productService;

    @Override
    public void getProductById(GetProductByIdRequest request, StreamObserver<GetProductByIdResponse> responseObserver) {
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        System.out.println("Processing request from " + clientId);
        ProductDTO dto = productService.getProductById(request.getProductId());
        if (validateProductQueryOutput(dto)) {
//            GetProductByIdResponse response = GetProductByIdResponse.newBuilder().setProductId(dto.getProductId()).setProductName(dto.getProductName()).setProductCategoryName(dto.getCategory()).setProductPrice(dto.getProductPrice()).build();
            GetProductByIdResponse response = GetProductByIdResponse.newBuilder().setProductId(dto.getProductId()).setProductName(dto.getProductName()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("no description of product with the requested id of " + request.getProductId()).asException());
            responseObserver.onCompleted();
        }
    }

    public boolean validateProductQueryOutput(ProductDTO dto) {
//        if (Objects.isNull(dto.getProductId()) || Objects.isNull(dto.getProductName()) || Objects.isNull(dto.getCategory()) || Objects.isNull(dto.getProductPrice())) {
//            return false;
//        }
        if (Objects.isNull(dto.getProductId()) || Objects.isNull(dto.getProductName()) || Objects.isNull(dto.getProductPrice())) {
            return false;
        }
        return true;
    }

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        System.out.println("Processing request from " + clientId);
        PingResponse response = PingResponse.newBuilder().setMessage(">>>>>PongKai>>>>>>").setTime(new Date().toString()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
//        super.ping(request, responseObserver);
    }
}
