package com.example.cuk_food.dto;

import com.example.cuk_food.entity.QnAEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QnADto {

    private Long qnaId; // QnA ID
    private Long productId; // 제품 ID
    private String question; // 질문 내용
    private String questionerName; // 질문 작성자 이름
    private String createdAt; // 작성 시간

    public static QnADto fromEntity(QnAEntity qnAEntity){
        return QnADto.builder()
                .qnaId(qnAEntity.getQnaId())
//                .productId(qnAEntity.getProduct().getProductId())
                .question(qnAEntity.getQuestion())
                .questionerName(qnAEntity.getQuestioner().getName()) // 작성자의 이름 포함
                .createdAt(qnAEntity.getCreatedAt().toString()) // 작성 시간 포함
                .build();
    }
}
