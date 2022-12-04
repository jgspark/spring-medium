package com.example.medium.config.redis;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.example.medium.enums.CacheTimePair;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisValue redisValue;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisValue.getHost(), redisValue.getPort());
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();

        RedisSerializationContext.SerializationPair<String> keySerializer = RedisSerializationContext.SerializationPair.fromSerializer(
            new StringRedisSerializer());

        RedisSerializationContext.SerializationPair<Object> valueSerializer = RedisSerializationContext.SerializationPair.fromSerializer(
            new JdkSerializationRedisSerializer());

        setRedisCacheConfigurationMap(redisCacheConfigurationMap, keySerializer, valueSerializer);

        RedisCacheConfiguration defaultConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).prefixKeysWith(
            "default").entryTtl(Duration.ofHours(5L));

        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        return new RedisCacheManager(redisCacheWriter, defaultConfiguration, redisCacheConfigurationMap);
    }

    private void setRedisCacheConfigurationMap(Map<String, RedisCacheConfiguration> redisCacheConfigurationMap,
                                               RedisSerializationContext.SerializationPair<String> keySerializer,
                                               RedisSerializationContext.SerializationPair<Object> valueSerializer) {
        Arrays.stream(CacheTimePair.values()).forEach(v -> {
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeKeysWith(
                keySerializer).serializeValuesWith(valueSerializer).disableCachingNullValues().prefixKeysWith("").entryTtl(
                Duration.ofSeconds(v.getTtl()));

            redisCacheConfigurationMap.put(v.name(), redisCacheConfiguration);
        });
    }
}
