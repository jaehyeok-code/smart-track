package com.project.smarttrack.dispatch.domain;

import com.project.smarttrack.common.domain.DispatchStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dispatches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispatch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_id", nullable = false)
  private Long orderId;

  @Column(name = "driver_id")
  private Long driverId;

  @Column(nullable = false)
  private DispatchStatus status;

  @Column(name = "assigned_at", nullable = false, updatable = false)
  private LocalDateTime assignedAt;

  @PrePersist
  public void prePersist(){

    this.assignedAt = LocalDateTime.now();

    if(this.status == null) {
      this.status = DispatchStatus.ASSIGNED;
    }
  }
}
