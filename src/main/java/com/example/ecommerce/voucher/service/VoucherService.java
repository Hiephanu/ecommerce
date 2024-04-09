package com.example.ecommerce.voucher.service;

import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.exception.NotFoundException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.voucher.model.dto.VoucherResponseDto;
import com.example.ecommerce.voucher.model.dto.VoucherUsingDto;
import com.example.ecommerce.voucher.model.entity.Voucher;
import com.example.ecommerce.voucher.model.enums.VoucherStatus;
import com.example.ecommerce.voucher.repository.VoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    public Result<Void, DomainException> useVoucher(VoucherUsingDto voucherUsingDto){
        Optional<Voucher> voucher = voucherRepository.findById(UUID.fromString(voucherUsingDto.getVoucherId()));
        if(voucher.isPresent()){
            voucher.get().setVoucherStatus(VoucherStatus.NONACTIVE);
            voucher.get().setUserId(UUID.fromString((voucherUsingDto.getUserId())));
            voucher.get().setProduct_id(UUID.fromString(voucherUsingDto.getProductId()));
            Voucher voucher1 = voucherRepository.save(voucher.get());
            return Result.success(null);
        } else {
            return Result.fail(new NotFoundException("Not found voucher"));
        }
    }
    public Result<VoucherResponseDto, NotFoundException> getVoucherById(String id){
        Optional<Voucher> voucher = voucherRepository.findById(UUID.fromString(id));
        return voucher.<Result<VoucherResponseDto, NotFoundException>>map(value -> Result.success(VoucherResponseDto.convertToVoucherResponseDto(value))).orElseGet(() -> Result.fail(new NotFoundException("Not found voucher")));
    }
    public Result<List<VoucherResponseDto>,NotFoundException> getListVoucherByUserId(String userId,int page,int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Voucher> vouchers = voucherRepository.findAllByUserId(UUID.fromString(userId),pageRequest);
        List<VoucherResponseDto> voucherResponseDtos = vouchers.stream().map(VoucherResponseDto::convertToVoucherResponseDto).toList();
        return Result.success(voucherResponseDtos);
    }
    public Result<Void,DomainException> deleteVoucher(String voucherId){
        voucherRepository.deleteById(UUID.fromString(voucherId));
        return Result.success(null);
    }

}
