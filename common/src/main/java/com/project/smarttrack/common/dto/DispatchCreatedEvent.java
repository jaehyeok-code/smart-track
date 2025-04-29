package com.project.smarttrack.common.dto;

import com.project.smarttrack.common.domain.DispatchStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DispatchCreatedEvent {

  private Long dispatchId;
  private Long orderId;
  private Long driverId;
  private DispatchStatus status;
  private LocalDateTime assignedAt;
}
