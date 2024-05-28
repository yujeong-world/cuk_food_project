package com.example.cuk_food.Service;

import com.example.cuk_food.Util.SecurityUtil;
import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import com.example.cuk_food.dto.ReviewDto;
import com.example.cuk_food.entity.Product;
import com.example.cuk_food.entity.Review;
import com.example.cuk_food.entity.UserEntity;
import com.example.cuk_food.repository.ProductRepository;
import com.example.cuk_food.repository.ReviewRepository;
import com.example.cuk_food.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository,UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository =userRepository;
    }

    // 1. 리뷰 등록
    public ReviewDto createReview(ReviewDto reviewDto) {

        // 현재 로그인한 사용자 정보 가져오기
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username); // 사용자 정보 없을 시 예외 발생
        }

        Product product = productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + reviewDto.getReviewId())); // 제품 정보 없을 시 예외 발생

        // 질문 엔티티 생성 및 저장
        Review review = new Review();
        review.setReviewer(user); // 질문 작성자 설정
        review.setProduct(product); // 관련 제품 설정
        review.setContext(reviewDto.getContext()); // 질문 내용 설정
        review = reviewRepository.save(review); // 엔티티 저장

        return ReviewDto.fromEntity(review); // 엔티티를 DTO로 변환하여 반환
    }


    // 2.리뷰 리스트 조회 , 상품 코드를 받아야 함
    public Page<ReviewDto> getReviewList(Long productId, Pageable pageable){
        Page<Review> reviews = reviewRepository.findByProduct_ProductId(productId, pageable); // 페이징 처리된 엔티티 목록 가져오기
        List<ReviewDto> reviewDtos = reviews.stream() // 엔티티를 DTO로 변환
                .map(ReviewDto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(reviewDtos, pageable, reviews.getTotalElements()); // DTO 리스트를 페이징 처리하여 반환
    }



}
