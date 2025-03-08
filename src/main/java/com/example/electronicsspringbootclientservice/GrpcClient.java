package com.example.electronicsspringbootclientservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GrpcClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagedChannel grpcChannel = ManagedChannelBuilder.forAddress("0.0.0.0", 50051).usePlaintext().build();

    public CategoryServiceGrpc.CategoryServiceBlockingStub initCategoryServiceStub() {
        logger.info("Opening gRPC Additional Client on channel");
        return CategoryServiceGrpc.newBlockingStub(grpcChannel);
    }

}
