package com.example.cuk_food.Service;

import com.example.cuk_food.Util.SecurityUtil;
import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import com.example.cuk_food.dto.QnADto;
import com.example.cuk_food.entity.Product;
import com.example.cuk_food.entity.QnAEntity;
import com.example.cuk_food.entity.UserEntity;
import com.example.cuk_food.repository.ProductRepository;
import com.example.cuk_food.repository.QnARepository;
import com.example.cuk_food.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Slf4j
@Service // 서비스 레이어로 선언
public class QnAService {

    private final QnARepository qnaRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public QnAService(QnARepository qnaRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.qnaRepository = qnaRepository; // QnA 레포지토리 주입
        this.userRepository = userRepository; // 사용자 레포지토리 주입
        this.productRepository = productRepository; // 제품 레포지토리 주입
    }

    // 1. QNA 등록
    public QnADto createQuestion(QnADto qnADto) {

        // 현재 로그인한 사용자 정보 가져오기
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username); // 사용자 정보 없을 시 예외 발생
        }

        Product product = productRepository.findById(qnADto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + qnADto.getProductId())); // 제품 정보 없을 시 예외 발생

        log.info("product : {}",product);

        // 질문 엔티티 생성 및 저장
        QnAEntity qnaEntity = new QnAEntity();
        qnaEntity.setQuestioner(user); // 질문 작성자 설정
        qnaEntity.setProduct(product); // 관련 제품 설정
        qnaEntity.setQuestion(qnADto.getQuestion()); // 질문 내용 설정
        qnaEntity = qnaRepository.save(qnaEntity); // 엔티티 저장

        return QnADto.fromEntity(qnaEntity); // 엔티티를 DTO로 변환하여 반환
    }

    // 2. QNA 조회
    public Page<QnADto> getQuestionsByProductId(Long productId, Pageable pageable) {
        Page<QnAEntity> qnaEntities = qnaRepository.findByProduct_ProductId(productId, pageable); // 페이징 처리된 엔티티 목록 가져오기
        List<QnADto> qnaDtos = qnaEntities.stream() // 엔티티를 DTO로 변환
                .map(QnADto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(qnaDtos, pageable, qnaEntities.getTotalElements()); // DTO 리스트를 페이징 처리하여 반환
    }

}
