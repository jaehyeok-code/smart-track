package com.project.smarttrack.common.dto;

import com.project.smarttrack.common.domain.OrderStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
  private Long orderId;
  private Long customerId;
  private String pickupLocation;
  private String destinationLocation;
  private OrderStatus status;
  private LocalDateTime createdAt;
}