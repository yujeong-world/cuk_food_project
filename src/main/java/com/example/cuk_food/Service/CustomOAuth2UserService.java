package com.example.cuk_food.Service;
import com.example.cuk_food.dto.Oauth2.*;
import com.example.cuk_food.entity.UserEntity;
import com.example.cuk_food.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 1. 초기화
        OAuth2Response oAuth2Response = null;


        // 2. 네이버일 경우
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        // 3. 구글 일 경우
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }

        // 4. 카카오 일 경우
        else if(registrationId.equals("kakao")){
            oAuth2Response = new KaKaoResponse(oAuth2User.getAttributes());
        }

        else {

            return null;
        }

        //1. 겹치지 않도록 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        UserEntity existData = userRepository.findByUsername(username);

        if (existData == null) { // 2.로그인을 한번도 안한 경우


            // 3.엔티티에 Oauth2 로부터 받은 리소스를 초기화해서 만듬
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");
            userEntity.setCreatedAt(LocalDateTime.now()); // 직접 설정

            // 4.유저 아이디를 리포지터리에 저장
            userRepository.save(userEntity);


            //5. 생성자 만듬
            UserDTO userDTO = new UserDTO();
            //6. 발급 받은 정보로 사용자를 특정할 아이디값로 변경
            userDTO.setUsername(username);
            //7. 실제 구글이나, 네이버의 이름 아이디 받아옴
            userDTO.setName(oAuth2Response.getName());
            //8. 임의로 유저 역할 설정
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }
        else {
            // 1.기존의 Oauth2 의 이메일과 닉네임이 바뀌었을수도 있으니 업데이트 진행
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            // 2.유저 아이디를 리포지터리에 저장
            userRepository.save(existData);


            // 3. 기존에 저장된 데이터 get 요청으로 가져옴.
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }
    }
}