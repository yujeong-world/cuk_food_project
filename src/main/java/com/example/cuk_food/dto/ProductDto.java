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
                .product_id(product.getProduct_id())
                .category_code(product.getCategory_code())
                .product_name(product.getProduct_name())
                .build();
    }
}
