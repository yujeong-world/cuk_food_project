package com.example.cuk_food.Service;

import com.example.cuk_food.entity.Cart;
import com.example.cuk_food.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // 장바구니 리스트 조회
    public List<Cart> getCart(){
        return cartRepository.findAll();
    }

}
