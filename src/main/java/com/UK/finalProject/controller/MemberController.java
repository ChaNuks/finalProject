package com.UK.finalProject.controller;

import com.UK.finalProject.dto.MemberDTO;
import com.UK.finalProject.entity.Member;
import com.UK.finalProject.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 멤버 조회
    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDTO> getMember (@PathVariable("id") long id) {
        MemberDTO memberDTO = memberService.findMemberById(id);
        if (memberDTO != null) {
            return ResponseEntity.ok(memberDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MemberDTO());
        }
    }

    // 회원가입
    @PostMapping("/signup")
    public String signUp (@RequestBody MemberDTO memberDTO) {
        return memberService.signUp(memberDTO);
    }

    // 로그인
    @PostMapping("/login")
    public String login (@RequestBody MemberDTO memberDTO, HttpSession session) {
        if (memberService.login(memberDTO.getEmail(), memberDTO.getPassword())) {
            session.setAttribute("member", memberDTO.getEmail());
            return "로그인 완료";
        }
        return "로그인 실패";
    }

    // 회원정보 수정
    @PutMapping("/member/{id}")
    public String updateMember(@PathVariable("id") long id, @RequestBody MemberDTO memberDTO) {
        return memberService.update(id, memberDTO);
    }

    // 회원 탈퇴
    @DeleteMapping("/member/{id}")
    public String deleteMember(@PathVariable("id") long id) {
        return memberService.delete(id);
    }

}
