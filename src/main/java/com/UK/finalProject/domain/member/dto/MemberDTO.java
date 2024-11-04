package com.UK.finalProject.domain.member.dto;

import com.UK.finalProject.domain.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data   // getter, setter, ToString 다 지원하는 어노테이션
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String image;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = "********";
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.image = member.getImage();
    }
}
