package com.example.cuk_food.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@NoArgsConstructor      // JPA ENTITY 기본 생성자 생성(필수)
@Getter
@Entity
public class Product extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Setter private String category_code;

    @Setter private String product_name;
}
