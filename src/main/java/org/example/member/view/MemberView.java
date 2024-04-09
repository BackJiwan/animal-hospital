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
    private final MemberRepository memberRepository;
    private final MemberService memberService;
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
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // 회원가입 로직 호출
                    //스캐너로 회원 정보 입력받기 가정
                    //입력받은 정보들에서 id,이름,생일 추출
//                    Date birthDate = Date.valueOf("2000-07-07");
//                    MemberDto memberDto = new MemberDto("id7","칠길동", birthDate);
//                    memberService.registerMember(memberDto);
//                    System.out.println(memberDto.getName()+"님 회원가입이 완료되었습니다.");
//                    break;
                    scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    String memberId;
                    while(true){
                        System.out.print("회원id : ");
                        memberId = scanner.nextLine();
                        if(memberId.isEmpty()||memberId.equals(" %")){
                            System.out.print("회원id를 입력해주세요\n");
                            continue;
                        }
                        break;
                    }

                    System.out.print("이름 : ");
                    String name = scanner.nextLine();


                    System.out.print("생일 : [ex)2000-07-02] ");
                    String birthInput = scanner.nextLine();
                    Date birthDate;

                    try {
                        birthDate = Date.valueOf(birthInput); // 입력받은 문자열을 Date로 변환
                    } catch (IllegalArgumentException e) {
                        System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
                        break; // 잘못된 입력 처리
                    }

                    // 입력 받은 정보로 MemberDto 생성
                    MemberDto memberDto = new MemberDto(memberId, name, birthDate);

                    // 회원가입 로직 호출
                    memberService.registerMember(memberDto);
                    System.out.println(name + "님 회원가입이 완료되었습니다.");
                    break;
                case 2:
                    scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("조회할 회원ID : ");
                    memberId = scanner.nextLine();
                    try {
                        Member foundMember = memberService.findMemberById(memberId);
                        System.out.println(foundMember.toString());
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                    }
                    break;
                case 3:
                    scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("수정할 회원ID : ");
                    memberId = scanner.nextLine();
                    try {
                        Member foundMember = memberService.findMemberById(memberId);
                        System.out.print("새 이름: ");
                        String newName = scanner.nextLine();
                        System.out.print("새 생일 [yyyy-MM-dd]: ");
                        String newBirth = scanner.nextLine();
                        Date newBirthDate = Date.valueOf(newBirth); // 입력받은 문자열을 Date로 변환
                        memberService.updateMember(new MemberDto(memberId, newName, newBirthDate));
                        System.out.println(memberId + " 회원 정보가 수정되었습니다.");
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
                    }
                    break;
                case 4:
                    scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("삭제할 회원ID : ");
                    memberId = scanner.nextLine();
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