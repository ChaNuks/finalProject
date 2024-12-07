package com.UK.finalProject.controller;

import com.UK.finalProject.common.auth.service.PasswordService;
import com.UK.finalProject.dto.MemberDTO;
import com.UK.finalProject.exception.CustomException;
import com.UK.finalProject.exception.ErrorCode;
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
    private final PasswordService passwordService;

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("data") String data) {
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.encodePassword(data));
    }

    // 멤버 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMember (@PathVariable("id") long id) {
        MemberDTO memberDTO = memberService.findMemberById(id);
        return ResponseEntity.ok(memberDTO);
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp (@RequestBody MemberDTO memberDTO) {

        if (memberDTO.getEmail().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_EMAIL);
        } else if (memberDTO.getEmail().length() > 50) {
            throw new CustomException(ErrorCode.INVALID_EMAIL);
        } else if (memberDTO.getPassword().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_PASSWORD);
        } else if (memberDTO.getName().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_NAME);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(memberDTO));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody MemberDTO memberDTO, HttpSession session) {
        if (memberService.login(memberDTO.getEmail(), memberDTO.getPassword())) {
            session.setAttribute("member", memberDTO.getEmail());
            return ResponseEntity.ok("로그인 완료");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
    }

    // 회원정보 수정
    @PutMapping("/member/{id}")
    public ResponseEntity<String> updateMember(@PathVariable("id") long id, @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.update(id, memberDTO));
    }

    // 회원 탈퇴
    @DeleteMapping("/member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") long id) {
        return ResponseEntity.ok(memberService.delete(id));
    }

}
