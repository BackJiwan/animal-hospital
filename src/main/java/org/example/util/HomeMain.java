package org.example.util;

import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import org.example.animal.controller.AnimalController;
import org.example.member.controller.MemberController;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;

@RequiredArgsConstructor
public class HomeMain {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public void displayHome() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*****************************************");
            System.out.println("************ 관리 시스템 홈화면 ************");
            System.out.println("*****************************************");
            System.out.println("1.회원메뉴");
            System.out.println("2.동물메뉴");
            System.out.println("3.치료내역조회");
            System.out.println("4.치료예약");
            System.out.println("5.시스템 종료");
            System.out.println("*****************************************");
            System.out.print("선택: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    MemberController memberController = new MemberController();
                    memberController.memberMenu();
                    break;
                case 2:
                    //동물 내역 조회
                    AnimalController animalController = new AnimalController();
                    animalController.animalMenu();
                    break;
                case 3:
                    // 치료내역조회 메서드 호출
                    break;
                case 4:
                    // 치료예약 메서드 호출
                    break;
                case 5:
                    System.out.println("시스템을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
