package org.example.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.member.view.MemberView;

@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository = new MemberRepository();
    private MemberService memberService = new MemberService(memberRepository);

    public void memberMenu() {
        MemberView view = new MemberView(memberRepository,memberService);
        view.memberView();
    }
}