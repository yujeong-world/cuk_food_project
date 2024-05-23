package com.example.cuk_food.oauth2;

import com.example.cuk_food.dto.Oauth2.CustomOAuth2User;
import com.example.cuk_food.entity.RefreshEntity;
import com.example.cuk_food.jwt.JWTUtil;
import com.example.cuk_food.repository.RefreshRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


@Slf4j
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public CustomSuccessHandler(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 유저 정보 가져오기
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        // 반복문을 사용하여 첫 번째 권한 가져오기
//        String role = null;
//        for (GrantedAuthority authority : customUserDetails.getAuthorities()) {
//            role = authority.getAuthority();
//            break; // 첫 번째 권한만 필요하므로 반복문을 종료합니다.
//        }

        String role = null;

        //GrantedAuthority 인터페이스를 구현한 객체들을 순회할 수 있는 이터레이터입니다.
        Iterator<? extends GrantedAuthority> iterator = customUserDetails.getAuthorities().iterator();

        //iterator.hasNext() 메서드는 이터레이터에 다음 요소가 있는지 확인합니다.
        //반환값은 boolean, 이터레이터에 다음 요소가 있으면 true, 없으면 false를 반환합니다
        if (iterator.hasNext()) {

            //iterator.next() 메서드는 이터레이터의 현재 위치에서 다음 요소를 반환하고,
            // 이터레이터의 위치를 다음 요소로 이동시킵니다.
            //첫 번째 호출 시, 컬렉션의 첫 번째 요소를 반환합니다. 그 후 호출할 때마다 다음 요소를 반환
            role = iterator.next().getAuthority();
        }


        log.info("유저 정보: username={}, role={}", username, role);

        // 액세스 토큰과 리프레시 토큰 생성
        String access = jwtUtil.createJwt("access", username, role, 60000L); // 1분
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L); // 1일

        log.info("엑세스 토큰: access={}", access);
        log.info("리프레쉬 토큰: refresh={}",refresh);

        // 리프레시 토큰 저장
        addRefreshEntity(username, refresh, 86400000L);

        // 응답 헤더에 액세스 토큰 추가
        response.setHeader("Authorization", "Bearer " + access);
        // 응답 쿠키에 리프레시 토큰 추가
        response.addCookie(createCookie("refresh", refresh));

        response.setStatus(HttpStatus.OK.value());

        // 리디렉션 경로 설정
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000/oauth2/redirect?access=" + access); // 프론트엔드로 리디렉션, 액세스 토큰 전달
    }

    private Cookie createCookie(String key, String value) {
        // 주어진 키와 값으로 새로운 쿠키 생성
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 쿠키 만료 시간 1일로 설정
        cookie.setSecure(false); // HTTPS 환경에서만 쿠키를 전송 (개발 환경에서는 false로 설정)
//        cookie.setHttpOnly(true); // 클라이언트 측 스크립트에서 쿠키 접근 불가능하도록 설정
        cookie.setPath("/"); // 애플리케이션 전체에 쿠키를 사용 가능하도록 설정
        log.info("Cookie created: key={}, value={}", key, value);
        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        // 리프레시 토큰을 데이터베이스에 저장

        Date date = new Date(System.currentTimeMillis() + expiredMs);
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
        log.info("Refresh token saved: username={}, refresh={}", username, refresh);
    }


}
