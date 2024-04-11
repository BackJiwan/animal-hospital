package org.example.util;

import java.util.Scanner;

import lombok.NoArgsConstructor;
import org.example.animal.controller.AnimalController;
import org.example.member.controller.MemberController;
import org.example.treatment.controller.TreatmentController;

@NoArgsConstructor
public class HomeMain {
    public void displayHome() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*****************************************");
            System.out.println("************ 관리 시스템 홈화면 ************");
            System.out.println("*****************************************");
            System.out.println("1.회원관리 메뉴");
            System.out.println("2.동물관리 메뉴");
            System.out.println("3.치료관리 메뉴");
            System.out.println("4.시스템 종료");
            System.out.println("*****************************************");
            System.out.print("선택: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    MemberController memberController = new MemberController();
                    memberController.memberMenu();
                    break;
                case 2:
                    AnimalController animalController = new AnimalController();
                    animalController.animalMenu();
                    break;
                case 3:
                    TreatmentController treatmentController = new TreatmentController();
                    treatmentController.treatmentMenu();
                    break;
                case 4:
                    System.out.println("시스템을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
