package com.project.smarttrack.dispatch.service;

import com.project.smarttrack.common.domain.DispatchStatus;
import com.project.smarttrack.common.dto.DispatchCreatedEvent;
import com.project.smarttrack.common.dto.OrderCancelledEvent;
import com.project.smarttrack.dispatch.domain.Dispatch;
import com.project.smarttrack.dispatch.repository.DispatchRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DispatchService {
  private final DispatchRepository dispatchRepository;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public List<Dispatch> getPending() {
    return dispatchRepository.findAllByStatus(DispatchStatus.ASSIGNED);
  }

  // 수락 처리
  @Transactional
  public void accept(Long dispatchId, Long driverId) {
    Dispatch d = dispatchRepository.findById(dispatchId)
        .orElseThrow(() -> new IllegalArgumentException("Dispatch not found: " + dispatchId));

    d.setStatus(DispatchStatus.IN_TRANSIT);
    d.setDriverId(driverId);
    dispatchRepository.save(d);

    DispatchCreatedEvent evt = DispatchCreatedEvent.builder()
        .dispatchId(d.getId())
        .orderId(d.getOrderId())
        .driverId(d.getDriverId())
        .status(d.getStatus())
        .assignedAt(d.getAssignedAt())
        .build();
    kafkaTemplate.send("dispatches", evt);
  }

  // 거절 처리
  @Transactional
  public void reject(Long dispatchId, Long driverId) {
    Dispatch d = dispatchRepository.findById(dispatchId)
        .orElseThrow(() -> new IllegalArgumentException("Dispatch not found: " + dispatchId));

    d.setStatus(DispatchStatus.REJECTED);
    d.setDriverId(driverId);
    dispatchRepository.save(d);

    OrderCancelledEvent cancelEvt = OrderCancelledEvent.builder()
        .orderId(d.getOrderId())
        .reason("기사 거절로 인한 취소")
        .cancelledAt(LocalDateTime.now())
        .build();
    kafkaTemplate.send("orders-cancelled", cancelEvt);
  }
}
