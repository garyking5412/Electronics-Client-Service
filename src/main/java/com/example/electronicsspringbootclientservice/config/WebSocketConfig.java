package com.example.electronicsspringbootclientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

//@Configuration
public class WebSocketConfig {

    private final WebSocketTextHandler webSocketTextHandler;

    public WebSocketConfig(WebSocketTextHandler webSocketService) {
        this.webSocketTextHandler = webSocketService;
    }

    @Bean
    public StandardWebSocketClient standardWebSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public WebSocketConnectionManager webSocketConnectionManager() {
        System.out.println("opening websocket >>>>");
        WebSocketConnectionManager webSocketConnectionManager = new WebSocketConnectionManager(standardWebSocketClient(), webSocketTextHandler, "ws://localhost:8082/ws");
        webSocketConnectionManager.setAutoStartup(true);
        return webSocketConnectionManager;
    }
}
