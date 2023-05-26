package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.gRPCService.PingService;
import com.example.electronicsspringbootclientservice.gRPCService.ProductGRPCServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class GrprServer {

    @Autowired
    private PingService pingService;

    @Autowired
    private ProductGRPCServiceImpl productGRPCService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @PostConstruct
    @Async
    @EventListener(ApplicationStartedEvent.class)
    public void startGrpcServer() throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(3006).addService(pingService).addService(productGRPCService).build();
        server.start();
        logger.info("gRPC Server is listening on port: " + server.getPort());
        server.awaitTermination();
    }
}
