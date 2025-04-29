package com.project.smarttrack.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequest {

  private Long customerId;
  private String pickupLocation;
  private String destinationLocation;

}
