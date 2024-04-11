package org.example.animal.controller;

import lombok.RequiredArgsConstructor;
import org.example.animal.service.AnimalService;
import org.example.animal.repository.AnimalRepository;
import org.example.animal.view.AnimalView;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;

@RequiredArgsConstructor
public class AnimalController {
    private final AnimalRepository animalRepository = new AnimalRepository();
    private AnimalService animalService = new AnimalService(animalRepository);
    private final MemberRepository memberRepository = new MemberRepository();
    private MemberService memberService = new MemberService(memberRepository);

    public void animalMenu() {
        AnimalView view = new AnimalView(animalRepository,animalService, memberRepository, memberService);
        view.animalView();
    }
}
