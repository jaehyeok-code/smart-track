package com.project.smarttrack.order.listener;

import com.project.smarttrack.common.domain.OrderStatus;
import com.project.smarttrack.common.dto.OrderCancelledEvent;
import com.project.smarttrack.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderCancelledListener {

  private final OrderRepository orderRepository;

  /**
   * 기사 거절 이벤트를 받아서 해당 주문의 상태를 CANCELLED 로 변경
   */
  @KafkaListener(topics = "orders-cancelled", groupId = "order-service-group")
  @Transactional
  public void handleOrderCancelled(OrderCancelledEvent evt) {
    orderRepository.findById(evt.getOrderId()).ifPresent(o -> {
      o.setStatus(OrderStatus.CANCELLED);
      o.setCancelledAt(evt.getCancelledAt());
      orderRepository.save(o);
    });
  }
}
