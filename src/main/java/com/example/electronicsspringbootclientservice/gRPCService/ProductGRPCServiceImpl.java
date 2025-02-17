package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.*;
import com.example.electronicsspringbootclientservice.service.ProductService;
import com.example.electronicsspringbootclientservice.utils.Constants;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

@GRpcService
@AllArgsConstructor
@Slf4j
public class ProductGRPCServiceImpl extends PingServiceGrpc.PingServiceImplBase {

    private final ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getProductById(GetProductByIdRequest request, StreamObserver<GetProductByIdResponse> responseObserver) {
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing request from [{}]", clientId);
        ProductDTO dto = productService.getProductById(request.getProductId());
        if (validateProductQueryOutput(dto)) {
            GetProductByIdResponse response = GetProductByIdResponse.newBuilder().setProductId(dto.getProductId()).setProductName(dto.getProductName()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("no description of product with the requested id of " + request.getProductId()).asException());
            responseObserver.onCompleted();
        }
    }

    public boolean validateProductQueryOutput(ProductDTO dto) {
        return !Objects.isNull(dto.getProductId()) && !Objects.isNull(dto.getProductName()) && !Objects.isNull(dto.getProductPrice());
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
