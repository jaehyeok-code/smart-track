package com.project.smarttrack.dispatch.listener;

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

    Long dummyDriverId = 100L;

    Dispatch dispatch = Dispatch.builder()
        .orderId(evt.getOrderId())
        .driverId(dummyDriverId)
        .build();
      Dispatch saved = dispatchRepository.save(dispatch);

    DispatchCreatedEvent createdEvt = DispatchCreatedEvent.builder()
        .dispatchId(saved.getId())
        .orderId(saved.getOrderId())
        .driverId(saved.getDriverId())
        .status(saved.getStatus())
        .assignedAt(saved.getAssignedAt())
        .build();

    kafkaTemplate.send("dispatches", createdEvt);

  }
}
