package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.gRPCService.CategoryGRPCServerImpl;
import com.example.electronicsspringbootclientservice.gRPCService.ProductGRPCServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GrpcServer {

    private final ProductGRPCServiceImpl productGRPCService;
    private final CategoryGRPCServerImpl categoryGRPCServer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Server server;

    @EventListener(ApplicationStartedEvent.class)
    public void startGrpcServer() throws InterruptedException, IOException {
        logger.info("Starting gRPC Additional Server >>");
        server = ServerBuilder.forPort(50052).addService(categoryGRPCServer).addService(productGRPCService).build();
        server.start();
        logger.info("gRPC Additional Server is listening on netty server port: {}", server.getPort());
        server.awaitTermination();
    }

    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }

}
