package com.example.cuk_food.repository;

import com.example.cuk_food.entity.UserAccount;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String>{
    Optional<UserAccount> findByUserId(String userId);
}
