package com.example.medium.service.cache;

import com.example.medium.config.redis.RedisConfiguration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 공통화된 캐시 작업을 위한 DefaultCache Class
 */
@Component
@RequiredArgsConstructor
public class DefaultCache {

    private final CacheManager cacheManager;

    private Optional<Cache.ValueWrapper> getOptional(RedisConfiguration.CacheTimePair pair, String key) {
        Assert.notNull(pair);
        Assert.notNull(key);
        return Optional.ofNullable(cacheManager.getCache(pair.name()).get(key));
    }

    private Object put(RedisConfiguration.CacheTimePair pair, String key, Object payload) {
        Objects.requireNonNull(cacheManager.getCache(pair.name())).put(key, payload);
        return payload;
    }

    public Optional<Object> get(RedisConfiguration.CacheTimePair pair, String key) {
        Optional<Cache.ValueWrapper> optional = getOptional(pair, key);
        return optional.map(Cache.ValueWrapper::get);
    }

    public <T> T getAndPut(RedisConfiguration.CacheTimePair pair, String key, Object payload) {
        Optional<Object> data = get(pair, key);
        if (data.isEmpty()) {
            put(pair, key, payload);
            return (T) payload;
        }
        return (T) data.get();
    }

    public <T> T getAndPut(RedisConfiguration.CacheTimePair pair, String key, Callable<T> callable) {

        Optional<Object> data = get(pair, key);

        if (data.isEmpty()) {

            T payload;

            try {
                payload = callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            put(pair, key, payload);

            return (T) payload;
        }
        return (T) data.get();
    }

    /**
     * 캐시 삭제
     * @param pair
     * @param key
     */
    public void remove(RedisConfiguration.CacheTimePair pair, String key) {
        cacheManager.getCache(pair.name()).evict(key);
    }
}
