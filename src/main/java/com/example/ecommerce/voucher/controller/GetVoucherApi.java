package com.example.ecommerce.voucher.controller;

import com.example.ecommerce.share.api.ResponseBody;
import com.example.ecommerce.share.api.ResponseEntityFactory;
import com.example.ecommerce.share.exception.NotFoundException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.voucher.model.dto.VoucherResponseDto;
import com.example.ecommerce.voucher.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
@AllArgsConstructor
public class GetVoucherApi {
    private final VoucherService  voucherService;
    @GetMapping("/getList")
    public ResponseEntity<ResponseBody> getListVoucherByUserId(@RequestParam String userId,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        try {
            Result<List<VoucherResponseDto>, NotFoundException> voucherResponseDtoDomainExceptionResult = voucherService.getListVoucherByUserId(userId,page,size);
            if(voucherResponseDtoDomainExceptionResult.isFailed()){
                return ResponseEntityFactory.badRequest(voucherResponseDtoDomainExceptionResult.failedData);
            } else {
                return ResponseEntityFactory.ok(voucherResponseDtoDomainExceptionResult.successData);
            }
        } catch (Exception e){
            return ResponseEntityFactory.internalServerError(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getVoucherById(@PathVariable String id){
        try {
            Result<VoucherResponseDto,NotFoundException> voucher = voucherService.getVoucherById(id);
            if(voucher.isFailed()){
                return ResponseEntityFactory.notFound(voucher.failedData);
            } else {
                return ResponseEntityFactory.ok(voucher.successData);
            }
        }catch (Exception e){
            return ResponseEntityFactory.internalServerError(e);
        }
    }
}
