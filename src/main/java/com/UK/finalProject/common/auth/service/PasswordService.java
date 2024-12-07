package com.UK.finalProject.common.auth.service;

// 사용자 패스워드 암호화 모듈

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService () {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }



    // 패스워드 암호화
    public String encodePassword(String originalPassword) {
     return passwordEncoder.encode(originalPassword);
    }
}
