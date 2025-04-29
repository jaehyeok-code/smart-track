package com.project.smarttrack.order.domain;

import com.project.smarttrack.common.domain.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long customerId;

  @Column(nullable = false)
  private String pickupLocation;

  @Column(nullable = false)
  private String destinationLocation;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist(){

    this.createdAt = LocalDateTime.now();

    if (this.status == null) {
      this.status = OrderStatus.RECEIVED;
    }
  }

}
