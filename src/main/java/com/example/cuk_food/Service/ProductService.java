package com.example.cuk_food.Service;

import com.example.cuk_food.dto.ProductDto;
import com.example.cuk_food.entity.Product;
import com.example.cuk_food.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


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


    //2. 조회
    @Transactional
    public Page<ProductDto> index(String searchKeyword, Pageable pageable) {
        Page<Product> products = null;
        if (searchKeyword == null || searchKeyword.isBlank()) {
            products = productRepository.findAll(pageable);
        }

        // Page<Product>을 Page<ProductDto>로 변환
        return Objects.requireNonNull(products).map(ProductDto::fromEntity);
    }

}
