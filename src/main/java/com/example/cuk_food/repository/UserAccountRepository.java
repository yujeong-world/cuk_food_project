package com.example.cuk_food.repository;
import com.example.cuk_food.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String>{
    Optional<UserAccount> findByUserId(String userId);
}
