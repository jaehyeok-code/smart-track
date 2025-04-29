package com.project.smarttrack.location.controller;

import com.project.smarttrack.common.dto.LocationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

  private final RedisTemplate<String, LocationEvent> redisTemplate;

  @GetMapping("/{dispatchId}")
  public ResponseEntity<LocationEvent> getLocation(@PathVariable Long dispatchId) {
    String key = "dispatch: " + dispatchId;
    LocationEvent evt = redisTemplate.opsForValue().get(key);
    if (evt == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(evt);
  }

}
