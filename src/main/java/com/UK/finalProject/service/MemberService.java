package com.UK.finalProject.service;

import com.UK.finalProject.dto.MemberDTO;
import com.UK.finalProject.entity.Member;
import com.UK.finalProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    // 이거 쓰면 생성자를 만들 필요가 없는데 에러가 떠서 만듦, 원인 찾아보기
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 조회
    public MemberDTO findMemberById(long id) {
        Member foundMember = memberRepository.findMemberById(id);
        if (foundMember == null) {
            System.out.println("해당 회원 정보를 찾을 수 없음");
        }
        return new MemberDTO(foundMember);

        // 멤버 정보가 없을 시 "해당 회원정보를 찾을 수 없음"이라고 출력하게 만들기
    }

    // 회원 가입
    public String signUp(MemberDTO memberDTO) {

        Member toSaveMember = new Member();
        toSaveMember.createMember(memberDTO.getEmail(), memberDTO.getPassword(), memberDTO.getName(), memberDTO.getNickname(), memberDTO.getImage());

        return memberRepository.signupMember(toSaveMember);
    }

    // 로그인
    public boolean login(String email, String password) {

        Member checkMember = memberRepository.findMemberByEmail(email);
        if (checkMember != null && checkMember.getPassword().equals(password)) {
            return true;
        }
        return false;
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
