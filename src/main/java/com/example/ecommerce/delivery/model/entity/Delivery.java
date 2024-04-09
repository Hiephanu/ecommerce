package com.example.ecommerce.delivery.model.entity;

import com.example.ecommerce.order.model.dto.DeliveryStatus;
import com.example.ecommerce.delivery.model.enums.DeliveryType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nameDelivery;
    private DeliveryType deliveryType;
    private double price;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime takenFromWarehouse;
    private LocalDateTime startDelivery;
    private LocalDateTime finishDelivery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
