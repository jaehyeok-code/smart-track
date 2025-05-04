package com.project.smarttrack.dispatch.listener;

import com.project.smarttrack.common.domain.DispatchStatus;
import com.project.smarttrack.common.dto.DispatchCreatedEvent;
import com.project.smarttrack.common.dto.OrderCreatedEvent;
import com.project.smarttrack.dispatch.domain.Dispatch;
import com.project.smarttrack.dispatch.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {

  private final DispatchRepository dispatchRepository;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @KafkaListener(topics = "orders", groupId = "dispatch-service-group")
  public void handleOrderCreated(OrderCreatedEvent evt) {

      Dispatch dispatch = Dispatch.builder()
          .orderId(evt.getOrderId())
          .driverId(0L)                  // 실제 배정은 나중에 기사수락 시점에 채움.
          .status(DispatchStatus.ASSIGNED) // ASSIGNED == 배정 대기
          .build();
      dispatchRepository.save(dispatch);
    }
  }

