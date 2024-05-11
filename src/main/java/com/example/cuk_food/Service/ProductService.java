package com.example.cuk_food.Service;

import com.example.cuk_food.dto.ProductDto;
import com.example.cuk_food.entity.Product;
import com.example.cuk_food.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //1. 생성
    public Product createProduct(ProductDto productDto) {
        // DTO를 엔티티로 변환
        Product product = new Product();
        product.setCategory_code(productDto.getCategory_code());
        product.setProduct_name(productDto.getProduct_name());

        // 상품 저장
        return productRepository.save(product);
    }
}
