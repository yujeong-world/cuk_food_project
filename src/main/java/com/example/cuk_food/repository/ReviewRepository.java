package com.example.cuk_food.repository;

import com.example.cuk_food.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 상품 코드로 리뷰 조회하기
    Page<Review> findByProduct_ProductId(Long productId, Pageable pageable);
}
