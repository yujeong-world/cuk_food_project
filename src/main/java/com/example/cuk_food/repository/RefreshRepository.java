package com.example.cuk_food.repository;

import com.example.cuk_food.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {

    // 리프레시 토큰 존재 여부 확인
    Boolean existsByRefresh(String refresh);

    // 리프레시 토큰 삭제
    @Transactional
    void deleteByRefresh(String refresh);
}