package com.example.electronicsspringbootclientservice.gRPCService;

import com.example.electronicsspringbootclientservice.PingRequest;
import com.example.electronicsspringbootclientservice.PingResponse;
import com.example.electronicsspringbootclientservice.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Date;

@GRpcService
public class PingService extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        PingResponse response = PingResponse.newBuilder().setMessage(">>>>>PongKai>>>>>>").setTime(new Date().toString()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
//        super.ping(request, responseObserver);
    }
}
