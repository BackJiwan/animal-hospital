package org.example.animal.view;

import lombok.RequiredArgsConstructor;
import org.example.animal.dto.AnimalDto;
import org.example.animal.entity.Animal;
import org.example.animal.repository.AnimalRepository;
import org.example.animal.service.AnimalService;
import org.example.member.dto.MemberDto;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;

import java.sql.Date;
import java.util.Scanner;

@RequiredArgsConstructor
public class AnimalView {
    private final AnimalRepository animalRepository;
    private final AnimalService animalService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    public void animalView() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. 동물등록");
            System.out.println("2. 동물정보조회");
            System.out.println("3. 동물정보수정");
            System.out.println("4. 동물삭제");
            System.out.println("5. 뒤로가기");
            System.out.print("선택: ");
            //문자열로 받는 이유는 nextInt() 가 자꾸 개행을 남겨두고 가져가서 문제가 생겼기 때문임
            String temp = scanner.nextLine(); // 사용자 입력을 문자열로 받음
            choice = Integer.parseInt(temp); //이후 정수로 바꿈

            switch (choice) {
                case 1:
                    //scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    String animalId;
                    String memberId;
                    String name;
                    String birthInput;
                    Date birthDate = null;
                    Date newBirthDate = null;
                    while(true){
                        //동물의 ID
                        System.out.print("동물id : ");
                        animalId = scanner.nextLine();
                        if(animalId.isEmpty()||animalId.contains(" ")
                                ||!animalId.matches("^[a-zA-Z0-9]*$")){
                            System.out.print("올바른 동물id를 입력해주세요\n");
                            continue;
                        }
                        animalId = "A_" + animalId;
                        break;
                    }

                    while(true){
                        //주인의 ID
                        System.out.print("회원id : ");
                        memberId = scanner.nextLine();
                        if(memberId.isEmpty()||memberId.contains(" ")
                                ||!memberId.matches("^[a-zA-Z0-9]*$")){
                            System.out.print("올바른 회원id를 입력해주세요.\n");
                            continue;
                        }
                        //주인 ID 생성은 주인메뉴에서 하기 때문에, 이곳에서는 단지 이미 있는 주인 ID
                        memberId = "M_" + memberId;
                        MemberDto existingMember = memberRepository.findMemberById(memberId);
                        if (existingMember == null) {
                            System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                            continue;
                        }
                        break;
                    }

                    while(true) {
                        System.out.print("이름 : ");
                        name = scanner.nextLine();
                        if (name.isEmpty()||!name.matches("^[a-zA-Z0-9가-힣]*$")) {
                            System.out.print("올바른 이름을 입력해주세요.\n");
                            continue;
                        }
                        name = "AN_" + name;
                        break;
                    }

                    while(true) {
                        System.out.print("생일 : [ex)2000-07-02] ");
                        birthInput = scanner.nextLine();

                        try {
                            birthDate = Date.valueOf(birthInput); // 입력받은 문자열을 Date로 변환
                        } catch (IllegalArgumentException e) {
                            System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
                            continue; // 잘못된 입력 처리
                        }
                        break;
                    }

                    System.out.print("종 [ex:골든 리트리버] : ");
                    String species = scanner.nextLine();

                    // 입력 받은 정보로 AnimalDto 생성
                    AnimalDto animalDto = new AnimalDto(animalId, memberId, name, birthDate, species);

                    // 등록 로직 호출
                    animalService.registerAnimal(animalDto);
                    System.out.println(name + "등록이 완료되었습니다.");
                    break;
                case 2:
                    //scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("조회할 동물ID : ");
                    animalId = scanner.nextLine();
                    animalId = "A_" + animalId;
                    try {
                        Animal foundAnimal = animalService.findAnimalById(animalId);
                        System.out.println("동물ID: " + foundAnimal.getAnimalId() + ", 주인ID: "+foundAnimal.getMemberId()+
                                ", 이름: " + foundAnimal.getName() + ", 생일: " + foundAnimal.getBirth()+", 종: "+foundAnimal.getSpecies());
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 동물이 존재하지 않습니다.");
                    }
                    break;
                case 3:
                    //scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("수정할 동물ID : ");
                    animalId = scanner.nextLine();
                    animalId = "A_" + animalId;
                    try {
                        //이름 받기
                        Animal foundAnimal = animalService.findAnimalById(animalId);
                        memberId = animalRepository.findMemberById(animalId).getMemberId();
                        String newName;
                        while(true) {
                            System.out.print("새 이름 : ");
                            newName = scanner.nextLine();
                            if (newName.isEmpty()||!newName.matches("^[a-zA-Z0-9가-힣]*$")) {
                                System.out.print("올바른 이름을 입력해주세요.\n");
                                continue;
                            }
                            newName = "AN_" + newName;
                            break;
                        }
                        //날짜 받기
                        while(true) {
                            System.out.print("새 생일 [yyyy-MM-dd]: ");
                            String newBirth = scanner.nextLine();
                            try {
                                newBirthDate = Date.valueOf(newBirth); // 입력받은 문자열을 Date로 변환
                            } catch (IllegalArgumentException e) {
                                System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
                                continue; // 잘못된 입력 처리
                            }
                            break;
                        }
                        System.out.print("새 종 : ");
                        String newSpecies = scanner.nextLine();
                        animalService.updateAnimal(new AnimalDto(animalId, memberId, newName, newBirthDate, newSpecies));
                        System.out.println(animalId + " 회원 정보가 수정되었습니다.");
                    } catch (NullPointerException e) {
                        System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("잘못된 날짜 포맷입니다. yyyy-MM-dd 형식으로 입력해주세요.");
                    }
                    break;
                case 4:
                    //scanner.nextLine(); // 이전 nextInt() 후 남은 개행문자 처리
                    System.out.print("삭제할 동물ID : ");
                    animalId = scanner.nextLine();
                    animalId = "A_" + animalId;
                    try {
                        animalService.deleteAnimal(animalId);
                        System.out.println(animalId + " 동물이 삭제되었습니다.");
                    } catch (Exception e) {
                        System.out.println("동물 삭제 중 오류가 발생했습니다: " + e.getMessage());
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
