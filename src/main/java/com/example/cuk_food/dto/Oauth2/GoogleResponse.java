package com.example.cuk_food.dto.Oauth2;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{


    // 1. 데이터를 받을 공간 하나 만들어놓음.
    private final Map<String, Object> attribute;

    // 2. 구글 예시
    //{
    //		resultcode=00, message=success, id=123123123, name=개발자유미
    //}

    public GoogleResponse(Map<String, Object> attribute) {

        this.attribute = attribute;
    }

    @Override
    public String getProvider() {

        return "google";
    }

    @Override
    public String getProviderId() {

        return attribute.get("sub").toString();
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