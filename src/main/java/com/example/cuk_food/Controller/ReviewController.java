package com.example.cuk_food.Controller;

import com.example.cuk_food.Service.ReviewService;
import com.example.cuk_food.dto.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @PostMapping("/review") // POST 요청을 처리하는 메서드
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto createdReviewDto = reviewService.createReview(reviewDto); // 질문 생성
        return ResponseEntity.ok(createdReviewDto); // 생성된 질문 DTO 반환
    }


    @GetMapping("/review")
    public ResponseEntity<Page<ReviewDto>> reviewList(@RequestParam Long productId, Pageable pageable){
        Page<ReviewDto> reviewDtos = reviewService.getReviewList(productId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(reviewDtos);
    }



}
