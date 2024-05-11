package com.example.cuk_food.repository;



import com.example.cuk_food.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //1. 카테고리 분류
    Page<Product> findByCategoryCode(String categoryCode, Pageable pageable);

    //2. 상품 이름 조회
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Product> findByProductNameContaining(String searchTerm, Pageable pageable);
}
