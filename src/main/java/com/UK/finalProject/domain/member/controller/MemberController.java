package com.UK.finalProject.domain.member.controller;

import com.UK.finalProject.domain.member.dto.MemberDTO;
import com.UK.finalProject.domain.member.entity.Member;
import com.UK.finalProject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 멤버 조회
    @GetMapping("/member/{id}")
    public MemberDTO getMember(@PathVariable("id") long id) {
        return memberService.findMemberById(id);
    }

}
