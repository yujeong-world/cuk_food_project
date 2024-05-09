package com.example.cuk_food.Service;

import com.example.cuk_food.entity.UserAccount;
import com.example.cuk_food.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Setter
public class UserAccountDetailService implements UserDetailsService {


    private final UserAccountRepository userAccountRepository;

    public UserAccountDetailService(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));

        // 리포지터리에 만약 위에 아이디 정보가 없으면 예외처리 되서어서 로그인안됌

        return org.springframework.security.core.userdetails.User.builder()
                .username(userAccount.getUserId())
                .password(userAccount.getUserPassword())
                // Add any additional roles or authorities here if needed
                .roles("USER")
                .build();
    }


    //사용자 id로 사용자 정보를 가져오는 메서드


}
