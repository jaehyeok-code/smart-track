package com.project.smarttrack.location.listener;

import com.project.smarttrack.common.dto.LocationEvent;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationEventListener {

  private final RedisTemplate<String, LocationEvent> redisTemplate;

  @KafkaListener(topics = "locations", groupId = "location-service-group")
  public void handleLocation(LocationEvent evt) {

    String key = "dispatch: " + evt.getDispatchId();

    //5분간 값 캐싱
    redisTemplate.opsForValue().set(key, evt, Duration.ofMinutes(5));
  }
}
