package com.UK.finalProject.domain.admin.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class Administrator {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String profileImage;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Builder
    public Administrator(Long id, String email, String password, String nickname, String name, String profileImage, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
