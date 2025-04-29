package com.project.smarttrack.order.service;

import com.project.smarttrack.common.dto.OrderCreatedEvent;
import com.project.smarttrack.order.domain.Order;
import com.project.smarttrack.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Transactional
  public Order createOrder(Order order) {

    Order saved = orderRepository.save(order);

    OrderCreatedEvent event = OrderCreatedEvent.builder()
        .orderId(saved.getId())
        .customerId(saved.getCustomerId())
        .pickupLocation(saved.getPickupLocation())
        .destinationLocation(saved.getDestinationLocation())
        .status(saved.getStatus())
        .createdAt(saved.getCreatedAt())
        .build();

    kafkaTemplate.send("orders", event);
    return saved;
  }
}
