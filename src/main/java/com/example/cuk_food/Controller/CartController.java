package com.example.cuk_food.Controller;

import com.example.cuk_food.Service.CartService;
import com.example.cuk_food.dto.CartDto;
import com.example.cuk_food.entity.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cart>> getCartList(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }

}
