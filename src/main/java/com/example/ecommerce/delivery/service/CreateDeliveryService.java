package com.example.ecommerce.delivery.service;

import com.example.ecommerce.delivery.model.entity.Delivery;
import com.example.ecommerce.delivery.repository.DeliveryRepository;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateDeliveryService {
    @Autowired
    private final DeliveryRepository deliveryRepository;
    public Result<Delivery, DomainException> createDelivery(Delivery delivery){
        Delivery delivery1 = deliveryRepository.save(delivery);
        return Result.success(delivery1);
    }
}
