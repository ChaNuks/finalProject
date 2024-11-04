package com.UK.finalProject.domain.admin.dto;

import lombok.Data;

@Data
public class AdminDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String profileImage;
}
