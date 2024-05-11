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

    //1. 생성(C)
    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setCategoryCode(productDto.getCategory_code());
        product.setProductName(productDto.getProduct_name());
        return productRepository.save(product);
    }


    //2. 카테고리 조회 (R)
    @Transactional
    public Page<ProductDto> getProducts(String categoryCode, String searchTerm, Pageable pageable) {
        // 2-1. 초기화
        Page<Product> products;

        // 2-2. 카테고리 null 또는 빈칸 && 상품이름 null 또는 빈칸 이면 전체 조회
        if ((categoryCode == null || categoryCode.isBlank()) && (searchTerm == null || searchTerm.isBlank())) {
            products = productRepository.findAll(pageable);

        // 2-3. 상품이름이 null 빈칸 아니면 상품 이름 조회
        } else if (searchTerm != null && !searchTerm.isBlank()) {
            products = productRepository.findByProductNameContaining(searchTerm, pageable);

        // 2-4. 그밖에는 카테고리 조회
        } else {
            products = productRepository.findByCategoryCode(categoryCode, pageable);
        }
        // 2-5. 위에 조건중 적합한 products 반환
        return products.map(ProductDto::fromEntity);
    }
}
