package com.example.electronicsspringbootclientservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
public class WebSocketTextHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WebSocketSession socketSession;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sendMessage("this is spring boot WebSocket client signing off >>>>>");
        logger.info("signing off with status : [{}] >>>>>", status);
        socketSession = null;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("signing in >>>>>");
        socketSession = session;
        sendMessage("Hello from client!");
    }

    public void sendMessage(String message) {
        if (socketSession != null && socketSession.isOpen()) {
            try {
                logger.info("Sending message: {}", message);
                socketSession.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                logger.error("Error sending message: {}", e.getMessage(), e);
            }
        } else {
            logger.warn("WebSocket session is not open. Cannot send message: {}", message);
        }
    }
}
