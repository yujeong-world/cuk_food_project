package com.example.cuk_food.dto;

import com.example.cuk_food.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {

    private Long product_id;
    private String category_code;
    private String product_name;

    public static ProductDto fromEntity(Product product){
        return ProductDto.builder()
                .product_id(product.getProductId())
                .category_code(product.getCategoryCode())
                .product_name(product.getProductName())
                .build();
    }
}
