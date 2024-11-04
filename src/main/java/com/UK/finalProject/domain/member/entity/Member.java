package com.UK.finalProject.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Member {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    public enum Status {
        active, inactive
    }

    @Builder
    public Member(Long id, String email, String password, String nickname, String name, String image, LocalDateTime createdAt, LocalDateTime updatedAt, Status status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
