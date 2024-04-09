package com.example.ecommerce.voucher.repository;

import com.example.ecommerce.voucher.model.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    Page<Voucher> findAllByUserId(UUID userId, Pageable pageable);
}
