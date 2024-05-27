package com.example.cuk_food.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QnAEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Setter private String question; // 질문 내용

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;        // 관련 상품


    @ManyToOne
    @JoinColumn(name = "questioner_id", nullable = false)
    private UserEntity questioner; // 질문 작성자


}
