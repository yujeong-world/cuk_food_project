package com.example.cuk_food.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
@Builder // 빌더 패턴 활성화를 위해 이 어노테이션 추가
@AllArgsConstructor // @Builder에 필요함, 이미 있으면 추가하지 않아도 됨
@Table
@Entity
public class UserAccount extends AuditingFields {
    @Id
    @Column(length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;
}
