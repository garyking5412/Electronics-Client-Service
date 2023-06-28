package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.gRPCService.PingService;
import com.example.electronicsspringbootclientservice.gRPCService.ProductGRPCServiceImpl;
import com.example.electronicsspringbootclientservice.utils.GrpcAuthServerInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.lognet.springboot.grpc.autoconfigure.OnGrpcServerEnabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class GrprServer {

//    @Autowired
//    private PingService pingService;

    @Autowired
    private ProductGRPCServiceImpl productGRPCService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @PostConstruct
//    @Async
//    @EventListener(ApplicationStartedEvent.class)
//    @OnGrpcServerEnabled
//    public void startGrpcServer() throws InterruptedException, IOException {
//        Server server = NettyServerBuilder.forPort(1900).addService(ServerInterceptors.intercept(productGRPCService,new GrpcAuthServerInterceptor())).build();
////        Server server = ServerBuilder.forPort(3004).addService(pingService).addService(productGRPCService).build();
//        server.start();
//        logger.info("gRPC Server is listening on netty server port: " + server.getPort());
//        server.awaitTermination();
//    }

}
