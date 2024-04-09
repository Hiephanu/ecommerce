package com.example.ecommerce.voucher.model.dto;

import com.example.ecommerce.voucher.model.entity.Voucher;
import com.example.ecommerce.voucher.model.enums.VoucherConditionType;
import com.example.ecommerce.voucher.model.enums.VoucherStatus;
import com.example.ecommerce.voucher.model.enums.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class VoucherResponseDto {
    private UUID id;
    private String title;
    private String shortDescription;
    private int value;
    private VoucherConditionType conditionType;
    private VoucherType voucherType;
    private VoucherStatus voucherStatus;
    public static VoucherResponseDto convertToVoucherResponseDto(Voucher voucher){
        return VoucherResponseDto.builder()
                .id(voucher.getId())
                .title(voucher.getTitle())
                .value(voucher.getValue())
                .shortDescription(voucher.getShortDescription())
                .conditionType(voucher.getVoucherConditionType())
                .voucherType(voucher.getVoucherType())
                .voucherStatus(voucher.getVoucherStatus())
                .build();
    }
}
