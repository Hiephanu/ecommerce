package com.example.ecommerce.voucher.model.entity;

import com.example.ecommerce.voucher.model.enums.VoucherConditionType;
import com.example.ecommerce.voucher.model.enums.VoucherStatus;
import com.example.ecommerce.voucher.model.enums.VoucherType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String shortDescription;
    private double value;
    private VoucherConditionType voucherConditionType;
    private UUID userId;
    private UUID shop_id;
    private UUID product_id;
    private double require;
    private VoucherType voucherType;
    private VoucherStatus voucherStatus;
    private LocalDateTime created_at;
    private LocalDateTime redemption_date;
}
