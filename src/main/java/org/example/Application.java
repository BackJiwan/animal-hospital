package org.example;

import org.example.member.dto.MemberDto;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;

import java.sql.Date;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!\n");
        System.out.println("ID: m001인 회원조회\n");
        MemberRepository repo = new MemberRepository();
        MemberDto tempDto = repo.findMemberById("m001");
        Member tempMember = Member.builder()
                .memberId(tempDto.getMemberId())
                .name(tempDto.getName())
                .birth(tempDto.getBirth())
                .build();
        System.out.println(tempMember.toString());

        System.out.println("새로운 회원 오길동 가입");
        Date birthDate = Date.valueOf("1999-07-07");
        MemberDto dto = new MemberDto("id5","오길동", birthDate);
        tempDto = repo.addMember(dto);
        tempDto = repo.findMemberById("id5");
        tempMember = Member.builder()
                .memberId(tempDto.getMemberId())
                .name(tempDto.getName())
                .birth(tempDto.getBirth())
                .build();
        System.out.println(tempMember.toString());

//        System.out.println("오길동 회원 삭제");
//        repo.deleteMember("id5");

    }
}