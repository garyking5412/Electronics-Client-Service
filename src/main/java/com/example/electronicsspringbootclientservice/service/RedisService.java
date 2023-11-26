package com.example.electronicsspringbootclientservice.service;

import com.example.electronicsspringbootclientservice.DTO.ProductDTO;
import com.example.electronicsspringbootclientservice.utils.Constants;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Gson gsonTemplate;
    private static final String PRODUCT_KEY = Constants.PRODUCT_KEY;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean saveProduct(ProductDTO dto) {
        try {
            redisTemplate.opsForHash().put(PRODUCT_KEY, dto.getProductId() + "-" + dto.getProductName(), gsonTemplate.toJson(dto));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> DTOlist = new ArrayList<>();
        try {
            redisTemplate.opsForHash().values(PRODUCT_KEY).forEach(data -> {
                ProductDTO dto = gsonTemplate.fromJson(data.toString(), ProductDTO.class);
                DTOlist.add(dto);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DTOlist;
    }

    public boolean deleteAllProducts() {
        try {
            redisTemplate.delete(PRODUCT_KEY);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return true;
    }
}
