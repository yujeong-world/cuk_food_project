package com.example.cuk_food.Service;

import com.example.cuk_food.dto.ReviewDto;
import com.example.cuk_food.entity.Product;
import com.example.cuk_food.entity.Review;
import com.example.cuk_food.repository.ProductRepository;
import com.example.cuk_food.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    // 리뷰 리스트 조회 , 상품 코드를 받아야 함
    public Page<ReviewDto> getReviewList(Long productCode, Pageable pageable){
        Page<Review> reviews;


        // 상품 조회
        Product product = productRepository.findById(productCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        // 리뷰 조회
        reviews = reviewRepository.findByProduct(product, pageable);

        return reviews.map(ReviewDto::fromEntity);


    }

}
