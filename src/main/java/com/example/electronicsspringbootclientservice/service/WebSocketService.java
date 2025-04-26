package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.CategoryDTO;
import com.example.electronicsspringbootclientservice.config.WebSocketTextHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//@Service
@RequiredArgsConstructor
public class WebSocketService extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebSocketTextHandler webSocketTextHandler;

    public void createCategory() throws JsonProcessingException {
        CategoryDTO categoryDTO = new CategoryDTO().withName("BANKAI").withDetail("MINAZUKI");
        String str = objectMapper.writeValueAsString(categoryDTO);
        logger.info("sending data [{}] to WebSocket Server >>>", str);
        webSocketTextHandler.sendMessage(str);
    }
}
