package com.example.electronicsspringbootclientservice.utils;

import io.grpc.Context;
import io.grpc.Metadata;
import org.springframework.beans.factory.annotation.Value;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class Constants {
    public static final String JWT_SIGNING_KEY = "L8hHXsaQOUjk5rg7XPGv4eL36anlCrkMz8CJ0i/8E/0=";
    public static final String BEARER_TYPE = "Bearer";
    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
    public static final Context.Key<String> CLIENT_ID_CONTEXT_KEY = Context.key("clientId");
    //    @Value("${excel.max.row.allowed}")
    public static final int MAX_EXCEL_ROW = 2000;
    public static final String PRODUCT_KEY = "PRODUCT";

    public static final String rabbitTopicExchangeName = "spring-boot-rabbitFanoutExchange";
    public static final String rabbitTopicRetryExchangeName = "spring-boot-rabbitTopicRetryExchangeName";

    private Constants() {
        throw new AssertionError();
    }
}
