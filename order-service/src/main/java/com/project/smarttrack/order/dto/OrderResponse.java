package com.project.smarttrack.order.dto;

import com.project.smarttrack.common.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
  private Long orderId;
  private OrderStatus status;

}
