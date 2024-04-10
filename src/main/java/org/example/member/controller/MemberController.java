package org.example.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.member.view.MemberView;

@RequiredArgsConstructor
public class MemberController {
    public void memberMenu() {
        MemberView view = new MemberView();
        view.memberView();
    }
}