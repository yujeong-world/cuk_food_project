package com.example.cuk_food.dto;

import com.example.cuk_food.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private Long reviewId;  // 리뷰 ID
    private Long productId; // 제품 ID
    private String context; // 리뷰 내용
    private String reviewerName; // 리뷰 작성자 이름
    private String createdAt; // 작성 시간


    public static ReviewDto fromEntity(Review review){
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .productId(review.getProduct().getProductId())
                .context(review.getContext())
                .reviewerName(review.getReviewer().getName())
                .createdAt(review.getCreatedAt().toString()) // 작성 시간 포함
                .build();
    }
}
