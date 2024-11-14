package com.UK.finalProject.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
        ACTIVE, INACTIVE
    }

    public void createMember(String email, String password, String nickname, String name, String image) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.image = image;
    }

    public void updateMemberInfo(String password, String nickname, String name, String image) {
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.image = image;
        this.updatedAt = LocalDateTime.now();
    }
}
