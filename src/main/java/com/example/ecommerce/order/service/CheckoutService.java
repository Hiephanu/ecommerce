package com.example.ecommerce.order.service;

import com.example.ecommerce.auth.model.entity.User;
import com.example.ecommerce.cart.model.entity.CartItem;
import com.example.ecommerce.cart.service.CartItemService;
import com.example.ecommerce.delivery.model.dto.DeliveryPostDto;
import com.example.ecommerce.delivery.model.entity.Delivery;
import com.example.ecommerce.delivery.service.CreateDeliveryService;
import com.example.ecommerce.order.model.dto.OrderReturnDto;
import com.example.ecommerce.product.model.dto.ProductDetailDto;
import com.example.ecommerce.product.service.GetProductService;
import com.example.ecommerce.share.exception.DomainException;
import com.example.ecommerce.share.exception.ValidException;
import com.example.ecommerce.share.model.Result;
import com.example.ecommerce.user.service.UserService;
import com.example.ecommerce.voucher.model.entity.Voucher;
import com.example.ecommerce.voucher.model.enums.VoucherType;
import com.example.ecommerce.voucher.service.CheckConditionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CheckoutService {
    private UserService userService;
    private CartItemService cartItemService;
    private GetProductService getProductService;
    private CheckConditionService checkConditionService;
    private CreateDeliveryService createDeliveryService;
    public Result<OrderReturnDto, DomainException> checkOut(String userId,String cartItemId, Voucher voucher, DeliveryPostDto deliveryDto){
        Result<CartItem,DomainException> cartItem =  cartItemService.getCartItemById(cartItemId);
        Result<Delivery,DomainException> deliveryCreate = createDeliveryService.createDelivery(DeliveryPostDto.convertToDelivery(deliveryDto));
        Result<ProductDetailDto,DomainException> product= getProductService.getProductById(cartItem.successData.getProduct().getId().toString());

        //check quantity
        if(cartItem.successData.getQuantity() > product.successData.getQuantity()){
            return Result.fail(new ValidException("Insufficient quantity for some products"));
        }

        //count total
        double totalPrice = 0;
        double totalPriceProduct = cartItem.successData.getQuantity()*product.successData.getPrice();

        //check condition
        if(!checkConditionService.checkByPrice(totalPriceProduct,voucher).successData){
            return Result.fail(new ValidException("Can not add voucher"));
        }

        //add voucher, delivery
        VoucherType voucherType = voucher.getVoucherType();
        if(voucherType.equals(VoucherType.DISCOUNT)){
            totalPriceProduct =totalPriceProduct - voucher.getValue();
            totalPrice = totalPriceProduct +  deliveryCreate.successData.getPrice();
        }
        if(voucherType.equals(VoucherType.DELIVERY)){
            totalPrice = totalPriceProduct;
        }

        Result<User,DomainException> user = userService.getUserById(UUID.fromString(userId));
        OrderReturnDto orderReturnDto = OrderReturnDto.builder()
                .user(user.successData)
                .cartItem(cartItem.successData)
                .voucher(voucher)
                .totalPrice(totalPrice)
                .build();


        return  Result.success(orderReturnDto);
    }
}
