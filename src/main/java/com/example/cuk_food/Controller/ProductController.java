package com.example.cuk_food.Controller;

import com.example.cuk_food.Service.ProductService;
import com.example.cuk_food.dto.ProductDto;
import com.example.cuk_food.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {



    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //1. 생성
    @PostMapping("/products")
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        try {
            // 1.ProductService에서 Product 엔티티 생성
            Product createdProduct = productService.createProduct(productDto);

            // 2.Product 엔티티를 ProductDto로 변환
            ProductDto createdProductDto = ProductDto.fromEntity(createdProduct);

            // 3.생성된 ProductDto 반환
            return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);

            // 4.예외시 에러 반환
        } catch (Exception e) {
            System.err.println("Error creating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //2. 읽기
    @GetMapping("/products")
    public ResponseEntity<Page<ProductDto>> getProducts(@RequestParam(required = false) String category,
                                                  Pageable pageable) {

        Page<ProductDto> productPage = productService.getProducts(category, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }

}
