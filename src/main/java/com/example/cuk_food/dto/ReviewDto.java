package com.example.cuk_food.dto;

import com.example.cuk_food.entity.Product;
import com.example.cuk_food.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private Long reviewId;
    private String context;
    private Product product;

    public static ReviewDto fromEntity(Review review){
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .context(review.getContext())
                .product(review.getProduct())
                .build();
    }
}
