
package com.example.cuk_food.Controller;

import com.example.cuk_food.entity.RefreshEntity;
import com.example.cuk_food.jwt.JWTUtil;
import com.example.cuk_food.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class ReissueController {
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReissueController.class);

    public ReissueController(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키에서 리프레시 토큰 가져오기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh".equals(cookie.getName())) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        if (refresh == null) {
            // 리프레시 토큰이 없으면 로그를 남기고 400 응답 반환
            logger.error("Refresh token is missing");
            return new ResponseEntity<>("Refresh token is missing", HttpStatus.BAD_REQUEST);
        }

        try {
            // 리프레시 토큰이 만료되었는지 확인
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            // 리프레시 토큰이 만료되었으면 로그를 남기고 400 응답 반환
            logger.error("Refresh token expired");
            return new ResponseEntity<>("Refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰의 카테고리가 "refresh"인지 확인
        String category = jwtUtil.getCategory(refresh);
        if (!"refresh".equals(category)) {
            // 토큰의 카테고리가 유효하지 않으면 로그를 남기고 400 응답 반환
            logger.error("Invalid refresh token");
            return new ResponseEntity<>("Invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // 리프레시 토큰이 데이터베이스에 존재하는지 확인
        boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {
            // 리프레시 토큰이 존재하지 않으면 로그를 남기고 400 응답 반환
            return new ResponseEntity<>("Invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // 리프레시 토큰에서 사용자 이름과 역할을 추출
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        // 새로운 액세스 토큰과 리프레시 토큰 생성
        String newAccess = jwtUtil.createJwt("access", username, role, 60000L); // 60초
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L); // 1일

        // 기존 리프레시 토큰 삭제 후 새로운 리프레시 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(username, newRefresh, 86400000L);

        // 응답 헤더에 새로운 토큰 추가
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        logger.info("New access token: {}", newAccess);
        logger.info("New refresh token: {}", newRefresh);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {
        // 주어진 키와 값으로 새로운 쿠키 생성
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 쿠키 만료 시간 1일로 설정
        cookie.setSecure(true); // 보안 쿠키 설정 (HTTPS에서만 전송)
//        cookie.setHttpOnly(true); // 클라이언트 측 스크립트에서 쿠키 접근 불가능하도록 설정
        cookie.setPath("/"); // 쿠키의 유효 경로 설정
        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());
        refreshRepository.save(refreshEntity);
    }
}

