package com.project.smarttrack.common.dto;

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
public class LocationEvent {

  private Long dispatchId;
  private double latitude;
  private double longitude;
  private LocalDateTime timestamp;

}
