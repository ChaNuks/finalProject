package com.UK.finalProject.dto;

import com.UK.finalProject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter, setter, ToString 다 지원하는 어노테이션
@AllArgsConstructor
@NoArgsConstructor
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
