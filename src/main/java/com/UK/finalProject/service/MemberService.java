package com.UK.finalProject.service;

import com.UK.finalProject.common.auth.service.PasswordService;
import com.UK.finalProject.dto.MemberDTO;
import com.UK.finalProject.entity.Member;
import com.UK.finalProject.exception.CustomException;
import com.UK.finalProject.exception.ErrorCode;
import com.UK.finalProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // 이거 쓰면 생성자를 만들 필요가 없는데 에러가 떠서 만듦, 원인 찾아보기
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordService passwordService;

    // 회원 조회
    public MemberDTO findMemberById(long id) {

        Member foundMember = memberRepository.findMemberById(id);

        if (foundMember == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        return new MemberDTO(foundMember);

        // 멤버 정보가 없을 시 "해당 회원정보를 찾을 수 없음"이라고 출력하게 만들기
    }

    // 회원 가입
    public String signUp(MemberDTO memberDTO) {

        if (isDuplicatedEmail(memberDTO.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }

        Member toSaveMember = new Member();

        String encodePassword = passwordService.encodePassword(memberDTO.getPassword());

        toSaveMember.createMember(memberDTO.getEmail(), encodePassword, memberDTO.getName(), memberDTO.getNickname(), memberDTO.getImage());
        return memberRepository.signupMember(toSaveMember);

    }

    // 이메일 중복 검사
    private boolean isDuplicatedEmail(String email) {

        Member foundMember = memberRepository.findMemberByEmail(email);

        if (foundMember != null) {
            return true;
        }
        return false;
    }


    // 로그인
    public boolean login(String email, String password) {


        // 존재하지 않을 때, 로그인 정보가 일치하지 않을 때 (email 또는 password가 틀렸을 때)
        Member checkMember = memberRepository.findMemberByEmail(email);

        if (checkMember == null) {
            throw new CustomException(ErrorCode.WRONG_EMAIL);
        } else if (!checkMember.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        } else {
            return true;
        }
    }

    // 회원정보 수정
    public String update(long id, MemberDTO memberDTO) {

        Member editMember = memberRepository.findMemberById(id);
        editMember.updateMemberInfo(memberDTO.getPassword(), memberDTO.getNickname(), memberDTO.getName(), memberDTO.getImage());
        return memberRepository.updateMember(editMember);
    }

    // 회원 탈퇴
    public String delete(long id) {

        return memberRepository.deleteMember(id);
    }
}
