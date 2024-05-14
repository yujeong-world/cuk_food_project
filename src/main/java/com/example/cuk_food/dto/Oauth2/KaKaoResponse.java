package com.example.cuk_food.dto.Oauth2;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class KaKaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute; // 생성자

    public KaKaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
        // System.out.println을 사용하여 데이터를 출력합니다.
        System.out.println("KaKaoResponse 객체 생성됨. attribute 맵에 저장된 데이터: " + attribute);
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        System.out.println("Provider ID: " + attribute.get("id").toString());
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        System.out.println("Email: " + kakaoAccount.get("email").toString());
        return kakaoAccount.get("email").toString(); // 이메일을 추출합니다.
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        System.out.println("Name: " + profile.get("nickname").toString());
        return profile.get("nickname").toString(); // 프로필에서 닉네임(이름)을 추출합니다.
    }
}
