package com.example.cuk_food.Controller;

import com.example.cuk_food.Service.ReviewService;
import com.example.cuk_food.dto.ProductDto;
import com.example.cuk_food.dto.ReviewDto;
import com.example.cuk_food.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<Page<ReviewDto>> reviewList(@PathVariable Long productId, Pageable pageable){
        Page<ReviewDto> reviewDtos = reviewService.getReviewList(productId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(reviewDtos);
    }

}
