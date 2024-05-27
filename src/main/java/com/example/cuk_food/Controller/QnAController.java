package com.example.cuk_food.Controller;
import com.example.cuk_food.Service.QnAService;
import com.example.cuk_food.dto.QnADto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // RESTful 컨트롤러
@RequestMapping("/api/qna") // QnA 관련 API 경로 설정
public class QnAController {

    private final QnAService qnaService;

    public QnAController(QnAService qnaService) { // QnAService 주입
        this.qnaService = qnaService;
    }

    @PostMapping("/question") // POST 요청을 처리하는 메서드
    public ResponseEntity<QnADto> createQuestion(@RequestBody QnADto qnADto) {
        QnADto createdQnaDto = qnaService.createQuestion(qnADto); // 질문 생성
        return ResponseEntity.status(HttpStatus.OK).body(createdQnaDto); // 생성된 질문 DTO 반환
    }

    @GetMapping("/question") // GET 요청을 처리하는 메서드
    public ResponseEntity<Page<QnADto>> getQuestions(@RequestParam Long productId, Pageable pageable) {
        Page<QnADto> questions = qnaService.getQuestionsByProductId(productId, pageable); // 질문 목록 가져오기
        return ResponseEntity.ok(questions); // 질문 목록 반환
    }
}