package org.example.member.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.member.entity.Member;
import org.example.member.dto.MemberDto;
import org.example.member.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(MemberDto memberDto) {
        // 여기서 중복 회원 검증 등의 로직을 추가할 수 있음
        memberRepository.addMember(memberDto);
        return Member.builder()
                .memberId(memberDto.getMemberId())
                .name(memberDto.getName())
                .birth(memberDto.getBirth())
                .build();
    }

    public Member findMemberById(String memberId) {
        MemberDto member = memberRepository.findMemberById(memberId);
        return Member.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .birth(member.getBirth())
                .build();

    }

    public void deleteMember(String memberId) {
        // 회원 존재 여부 등의 검증 로직 추가 가능
        memberRepository.deleteMember(memberId);
    }

    // 치료접수 등의 추가적인 메서드 구현 가능
}
