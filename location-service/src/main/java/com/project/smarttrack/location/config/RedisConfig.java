package com.project.smarttrack.location.config;

import com.project.smarttrack.common.dto.LocationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, LocationEvent> redisTemplate(
      RedisConnectionFactory cf,
      Jackson2JsonRedisSerializer<LocationEvent> serializer
  ) {
    RedisTemplate<String, LocationEvent> tpl = new RedisTemplate<>();
    tpl.setConnectionFactory(cf);
    tpl.setDefaultSerializer(serializer);
    return tpl;
  }

  @Bean
  public Jackson2JsonRedisSerializer<LocationEvent> redisEventSerializer() {
    return new Jackson2JsonRedisSerializer<>(LocationEvent.class);
  }
}

