package com.example.cuk_food.jwt;
import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import com.example.cuk_food.dto.Oauth2.UserDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Processing request URI: {}", request.getRequestURI());

        // 로그인, Oauth2 엔드포인트에서는 JWT 검증이 필요없다.
        String requestUri = request.getRequestURI();
        if (requestUri.matches("^\\/login(?:\\/.*)?$") ||
                requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
            log.info("Skipping JWT filter for login or oauth2 endpoints.");
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 액세스 토큰 가져오기
        String accessToken = request.getHeader("Authorization");
        log.info("1.accessToken : {}", accessToken);

        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // "Bearer " 접두사 제거
        accessToken = accessToken.substring(7);
        log.info("2.accessToken : {}", accessToken);


        try {
            // 액세스 토큰이 만료되었는지 확인
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            // 액세스 토큰이 만료되었으면 401 응답 반환
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        // 토큰의 카테고리가 "access"인지 확인
        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals("access")) {
            // 토큰의 카테고리가 유효하지 않으면 401 응답 반환
            PrintWriter writer = response.getWriter();
            log.info("Invalid token category: {}", category);
            writer.print("invalid access token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("3.accessToken : {}", accessToken);

        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        // 토큰에서 사용자 이름과 역할 추출
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);

        // 사용자 인증 토큰 생성
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
