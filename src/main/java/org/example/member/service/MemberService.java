package org.example.member.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.member.entity.Member;
import org.example.member.dto.MemberDto;
import org.example.member.repository.MemberRepository;
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

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

    public Member updateMember(MemberDto memberDto) {
        // 회원 존재 여부 검증
        MemberDto existingMember = memberRepository.findMemberById(memberDto.getMemberId());
        if (existingMember == null) {
            System.out.println("해당 ID의 회원이 존재하지 않습니다.");
            return null;
        }

        // 회원 정보 업데이트
        try {
            memberRepository.updateMember(memberDto);
            System.out.println("회원 정보가 성공적으로 업데이트 되었습니다.");
            return Member.builder()
                    .memberId(memberDto.getMemberId())
                    .name(memberDto.getName())
                    .birth(memberDto.getBirth())
                    .build(); // 수정된 Member 객체 반환
        } catch (Exception e) {
            System.out.println("회원 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return null;
    }

    public void deleteMember(String memberId) {
        // 회원 존재 여부 등의 검증 로직 추가 가능
        memberRepository.deleteMember(memberId);
    }

    // 치료접수 등의 추가적인 메서드 구현 가능
}
