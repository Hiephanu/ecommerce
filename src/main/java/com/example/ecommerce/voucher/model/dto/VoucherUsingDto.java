package com.example.ecommerce.voucher.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class VoucherUsingDto {
    private String voucherId;
    private String userId;
    private String productId;
}
