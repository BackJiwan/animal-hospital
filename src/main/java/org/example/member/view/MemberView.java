package org.example.member.view;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.member.dto.MemberDto;

import java.sql.Date;
import java.util.Scanner;
@RequiredArgsConstructor
public class MemberView {
    private final MemberRepository memberRepository = new MemberRepository();
    private MemberService memberService = new MemberService(memberRepository);

    private String getId(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("올바른 값을 입력해주세요. 빈 문자열 또는 공백을 포함할 수 없습니다.");

            }
        } while (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9]*$"));
        return input;
    }

    //언더바가 포함된 회원아이디를 입력받을때에는 기존의 정규식으로 필터링 하면(getId) 언더바때문에 문제가 생김
    //DB에서 찾기위한 ID를 사용자로부터 입력받을때에만 searchId 메서드를 사용
    private String searchId(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9_]*$")) {
                System.out.println("올바른 값을 입력해주세요. 빈 문자열 또는 공백을 포함할 수 없습니다.");

            }
        } while (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9_]*$"));
        return input;
    }

    private String getName(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9가-힣]*$")) {
                System.out.println("올바른 값을 입력해주세요. 빈 문자열 또는 공백을 포함할 수 없습니다.");
            }
        } while (input.isEmpty() || input.contains(" ")||!input.matches("^[a-zA-Z0-9가-힣]*$"));
        return input;
    }

    private Date getDate(Scanner scanner, String prompt) {
        Date date = null;
        boolean isValidDate = false;

        while (!isValidDate) {
            System.out.print(prompt);
            String dateInput = scanner.nextLine().trim();

            // 빈 문자열이나 공백을 포함한 입력을 방지
            if (dateInput.isEmpty() || dateInput.contains(" ")) {
                System.out.println("올바른 값을 입력해주세요. 빈 문자열 또는 공백을 포함할 수 없습니다.");
                continue;
            }

            try {
                date = Date.valueOf(dateInput);
                isValidDate = true;
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
            }
        }
        return date;
    }
    public void memberView() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. 회원가입");
            System.out.println("2. 회원정보조회");
            System.out.println("3. 회원정보수정");
            System.out.println("4. 회원삭제");
            System.out.println("5. 뒤로가기");
            System.out.print("선택: ");
            //문자열로 받는 이유는 nextInt() 가 자꾸 개행을 남겨두고 가져가서 문제가 생겼기 때문임
            String temp = scanner.nextLine(); // 사용자 입력을 문자열로 받음
            choice = Integer.parseInt(temp); //이후 정수로 바꿈


            switch (choice) {
                case 1:
                    String memberId = getId(scanner, "회원id : ");
                    String name = getName(scanner, "이름 : ");
                    Date birthDate = getDate(scanner,"생일 [ex)2000-01-01] : ");
                    memberId = "M_" + memberId;
                    name = "MN_"+name;

                    MemberDto memberDto = new MemberDto(memberId, name, birthDate);
                    memberService.registerMember(memberDto);
                    System.out.println(name + "님 회원가입이 완료되었습니다.");
                    break;
                case 2:
                    memberId = searchId(scanner, "조회할 회원ID : ");
                    memberId = "M_" + memberId;
                    try {
                        Member foundMember = memberService.findMemberById(memberId);
                        System.out.println("회원ID: " + foundMember.getMemberId() + ", 이름: " + foundMember.getName() + ", 생일: " + foundMember.getBirth());
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                    }
                    break;
                case 3:
                    memberId = searchId(scanner, "수정할 회원ID : ");
                    memberId = "M_"+memberId;
                    try {
                        Member foundMember = memberService.findMemberById(memberId);
                        String newName = getName(scanner,"새 이름: ");

                        newName = "MN_"+newName;
                        Date newBirthDate = getDate(scanner,"새 생일 [ex)2000-01-01] : ");
                        memberService.updateMember(new MemberDto(memberId, newName, newBirthDate));
                        System.out.println(memberId + " 회원 정보가 수정되었습니다.");
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                    }
                    break;
                case 4:
                    memberId = getId(scanner, "삭제할 회원ID : ");
                    memberId = "M_"+memberId;
                    try {
                        memberService.deleteMember(memberId);
                        System.out.println(memberId + " 회원이 삭제되었습니다.");
                    } catch (Exception e) {
                        System.out.println("회원 삭제 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("메인 화면으로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        } while (choice != 5);
    }

}