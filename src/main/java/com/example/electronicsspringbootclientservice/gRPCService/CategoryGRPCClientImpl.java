package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.CategoryRequest;
import com.example.electronicsspringbootclientservice.CategoryResponse;
import com.example.electronicsspringbootclientservice.CategoryServiceGrpc;
import com.example.electronicsspringbootclientservice.GrpcClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
@AllArgsConstructor
@Slf4j
public class CategoryGRPCClientImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GrpcClient grpcClient;

    public CategoryResponse getCategory(CategoryRequest request) {
        CategoryServiceGrpc.CategoryServiceBlockingStub stub = grpcClient.initCategoryServiceStub();
        logger.info("received signal from grpc client >>> calling server");
        return stub.getCategory(request);
    }
}
