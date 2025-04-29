package com.project.smarttrack.order.service;

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
  public Order creatOrder(Order order) {

    Order saved = orderRepository.save(order);

    kafkaTemplate.send("orders", saved.getId().toString(), saved);
    return saved;

  }
}
