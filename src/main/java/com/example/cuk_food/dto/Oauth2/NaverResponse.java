package com.example.cuk_food.dto.Oauth2;

import java.util.Map;

public class NaverResponse implements OAuth2Response{


    // 1. 데이터를 받을 공간 하나 만들어놓음.
    private final Map<String, Object> attribute;

    //2. 네이버 예시
    //{
    //		resultcode=00, message=success, response={id=123123123, name=개발자유미}
    //}

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {

        return "naver";
    }

    @Override
    public String getProviderId() {

        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {

        return attribute.get("name").toString();
    }
}