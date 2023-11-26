package com.example.electronicsspringbootclientservice;

import com.example.electronicsspringbootclientservice.gRPCService.PingService;
import com.example.electronicsspringbootclientservice.gRPCService.ProductGRPCServiceImpl;
import com.example.electronicsspringbootclientservice.utils.GrpcAuthServerInterceptor;
import io.grpc.*;
import io.grpc.inprocess.InProcessSocketAddress;
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
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

@Component
public class GrprServer {

//    @Autowired
//    private PingService pingService;

    @Autowired
    private ProductGRPCServiceImpl productGRPCService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @PostConstruct
    @Async
//    @EventListener(ApplicationStartedEvent.class)
    @OnGrpcServerEnabled
    public void startGrpcServer() throws InterruptedException, IOException {
//        Server server = NettyServerBuilder.forPort(1900).addService(ServerInterceptors.intercept(productGRPCService,new GrpcAuthServerInterceptor())).build();
//        Server server = ServerBuilder.forPort(3004).addService(pingService).addService(productGRPCService).build();
        Server server;
        SocketAddress address = new InetSocketAddress("192.168.1.103",5058);
        server = NettyServerBuilder.forAddress(address).addService((BindableService) productGRPCService).build();
        server.start();
        logger.info("gRPC Additional Server is listening on netty server port: " + server.getPort());
        server.awaitTermination();
    }

}
