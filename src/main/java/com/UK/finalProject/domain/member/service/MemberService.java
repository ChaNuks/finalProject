package com.UK.finalProject.domain.member.service;

import com.UK.finalProject.domain.member.dto.MemberDTO;
import com.UK.finalProject.domain.member.entity.Member;
import com.UK.finalProject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor    // 이거 쓰면 생성자를 만들 필요가 없는데 에러가 떠서 만듦, 원인 찾아보기
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 조회
    public MemberDTO findMemberById(long id) {
        Member foundMember = memberRepository.findMemberById(id);
        return new MemberDTO(foundMember);

        // 멤버 정보가 없을 시 "해당 회원정보를 찾을 수 없음"이라고 출력하게 만들기
    }
}
