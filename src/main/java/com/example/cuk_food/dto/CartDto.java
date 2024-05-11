package com.example.cuk_food.dto;

import com.example.cuk_food.entity.Cart;
import com.example.cuk_food.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartDto {
    private Long cart_id;
    private Product product;
    private int qty;

    public static CartDto fromEntity(Cart cart){
        return CartDto.builder()
                .cart_id(cart.getCart_id())
                .product(cart.getProduct())
                .qty(cart.getQty())
                .build();
    }
}
