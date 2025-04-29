package com.project.smarttrack.order.controller;

import com.project.smarttrack.order.domain.Order;
import com.project.smarttrack.order.dto.OrderRequest;
import com.project.smarttrack.order.dto.OrderResponse;
import com.project.smarttrack.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
    Order order = new Order();
    order.setCustomerId(request.getCustomerId());
    order.setPickupLocation(request.getPickupLocation());
    order.setDestinationLocation(request.getDestinationLocation());

    Order saved = orderService.createOrder(order);
    OrderResponse response = new OrderResponse(saved.getId(), saved.getStatus());
    return ResponseEntity.status(201).body(response);
  }

}
