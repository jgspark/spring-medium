package com.example.medium.service;

import com.example.medium.config.redis.RedisConfiguration;
import com.example.medium.service.cache.DefaultCache;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DefaultCache defaultCache;

    private final String key = "product";

    public String test(String value) {

        String relKey = key + "_" + value;

        return defaultCache.getAndPut(RedisConfiguration.CacheTimePair.MIN_1, relKey, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return value;
            }
        });
    }
}
