package com.example.cuk_food.repository;

import com.example.cuk_food.entity.QnAEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QnARepository extends JpaRepository<QnAEntity, Long> {
    Page<QnAEntity> findByProduct_ProductId(Long productId, Pageable pageable);
}
