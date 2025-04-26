package com.example.electronicsspringbootclientservice;

import grpc.CategoryServiceGrpc;
import grpc.ChatMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GrpcClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ManagedChannel grpcChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext().build();


    @PostConstruct
    public void start() {
        logger.info("starting grpc data stream post construct");
        ManagedChannel grpcChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        CategoryServiceGrpc.CategoryServiceStub categoryServiceStub = CategoryServiceGrpc.newStub(grpcChannel);
        StreamObserver<ChatMessage> streamObserver = categoryServiceStub.chat(new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage chatMessage) {
                logger.info("received message from server: {}", chatMessage.toString());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
        streamObserver.onNext(ChatMessage.newBuilder().setSender("java-app").build());
        logger.info("ChatClient initialized and stream opened.");
    }

    public CategoryServiceGrpc.CategoryServiceBlockingStub initCategoryServiceStub() {
        logger.info("Opening gRPC Additional Client on channel");
        return CategoryServiceGrpc.newBlockingStub(grpcChannel);
    }

}
