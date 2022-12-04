package com.example.medium.repository.cache;

import com.example.medium.config.redis.RedisConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;

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

    /**
     * @param pair    패어 키값
     * @param key     레디스 키값
     * @param payload 캐시 값
     * @param <T>     캐시의 return 값
     * @return T
     */
    public <T> T getAndPut(RedisConfiguration.CacheTimePair pair, String key, Object payload) {
        Optional<Object> data = get(pair, key);
        if (data.isEmpty()) {
            put(pair, key, payload);
            return (T) payload;
        }
        return (T) data.get();
    }

    /**
     * 캐시 값을 callback 으로 한다.
     *
     * @param pair     패어 키값
     * @param key      레디스 키값
     * @param callable callback method
     * @param <T>      return 값
     * @return Optional<T>
     */
    public <T> Optional<T> getAndPut(RedisConfiguration.CacheTimePair pair, String key, Callable<T> callable) {

        Optional<Object> data = get(pair, key);

        if (data.isEmpty()) {

            Optional<T> payload;

            try {
                payload = Optional.ofNullable(callable.call());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (payload.isEmpty()) {
                return Optional.empty();
            }

            T cacheValue = payload.get();

            put(pair, key, cacheValue);

            return Optional.of((T) cacheValue);
        }

        return Optional.of((T) data.get());
    }

    /**
     * 캐시 삭제
     *
     * @param pair TTL 명칭
     * @param key  Redis Key 값
     */
    @Nullable
    public <T> Optional<T> remove(RedisConfiguration.CacheTimePair pair, String key) {
        cacheManager.getCache(pair.name()).evict(key);
        return Optional.empty();
    }
}
