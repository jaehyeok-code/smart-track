package com.project.smarttrack.order.dto;

import com.project.smarttrack.order.domain.Status;
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
  private Status status;

}
