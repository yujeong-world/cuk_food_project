package com.example.cuk_food.Service;

import com.example.cuk_food.dto.ReviewDto;
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

        // 전체 조회, 상품 코드를 먼저 조회하여 리뷰가 있는지 확인
        boolean isExitReview = productRepository.existsById(productCode);

        if(isExitReview != false){
            reviews = reviewRepository.findByProduct(productCode, pageable);

        }else{
            throw new RuntimeException("데이터가 존재하지 않습니다.");
        }
        return reviews.map(ReviewDto::fromEntity);


    }

}
