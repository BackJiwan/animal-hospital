package org.example.treatment.view;

import lombok.RequiredArgsConstructor;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.treatment.dto.TreatmentDto;
import org.example.treatment.entity.Treatment;
import org.example.treatment.repository.TreatmentRepository;
import org.example.treatment.service.TreatmentService;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@RequiredArgsConstructor
public class TreatmentView {
    private final TreatmentService treatmentService;
    private final TreatmentRepository treatmentRepository;

    private String getId(Scanner scanner, String prompt) {
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

    private String generateTreatmentId() {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        return "TR_" + String.format("%06d", randomNumber);
    }

    public void treatmentView(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        String idInput;
        String type;
        String treatId;
        String animalId;
        String memberId;
        Date day;
        List<Treatment> treatments;

        do {
            System.out.println("1. 치료접수");
            System.out.println("2. 치료내용수정");
            System.out.println("3. 치료예약취소");
            System.out.println("4. 치료내역조회");
            System.out.println("5. 뒤로가기");
            System.out.print("선택: ");
            //문자열로 받는 이유는 nextInt() 가 자꾸 개행을 남겨두고 가져가서 문제가 생겼기 때문임
            String temp = scanner.nextLine(); // 사용자 입력을 문자열로 받음
            choice = Integer.parseInt(temp); //이후 정수로 바꿈

            switch (choice) {
                case 1:
                    treatId = generateTreatmentId();
                    animalId = getId(scanner, "반려동물 ID: ");
                    animalId = "A_"+animalId;
                    memberId = getId(scanner, "회원 ID: ");
                    memberId = "M_"+memberId;
                    day = getDate(scanner, "치료 날짜 [yyyy-MM-dd]: ");
                    treatmentService.registerTreatment(new TreatmentDto(treatId, animalId, memberId, day, "진단전", "조치전", 0));
                    System.out.println("치료가 접수되었습니다. 치료 ID: " + treatId);
                    break;
                case 2:
                    idInput = getId(scanner, "치료내용을 수정할 반려동물 또는 회원의 ID 입력(M_XXXXX/A_XXXXXX): ");
                    type = idInput.startsWith("M_") ? "Member" : "Animal";

                    treatments = treatmentService.findTreatmentsById(idInput, type);
                    if (treatments.isEmpty()) {
                        System.out.println("해당 ID로 조회된 치료 내역이 없습니다.");
                        break;
                    }

                    System.out.println("조회된 치료 내역:");
                    for (Treatment treatment : treatments) {
                        System.out.println("치료ID: " + treatment.getTreatId() + ", 날짜: " + treatment.getDay() + ", 병명: " + treatment.getDisease() + ", 조치: " + treatment.getTreat() + ", 비용: " + treatment.getCost());
                    }

                    String selectedId = getId(scanner, "수정할 치료 내역의 치료ID 입력: ");
                    Treatment selectedTreatment = treatments.stream()
                            .filter(t -> t.getTreatId().equals(selectedId))
                            .findFirst()
                            .orElse(null);

                    if (selectedTreatment == null) {
                        System.out.println("선택한 치료ID에 해당하는 내역이 없습니다.");
                        break;
                    }

                    Date newDay = getDate(scanner, "새로운 치료 날짜 [yyyy-MM-dd]: ");
                    String newDisease = getName(scanner, "새로운 병명: ");
                    String newTreat = getName(scanner, "새로운 조치: ");
                    int newCost = Integer.parseInt(getId(scanner, "새로운 비용: "));

                    TreatmentDto updatedTreatment = new TreatmentDto(selectedTreatment.getTreatId(), selectedTreatment.getAnimalId(), selectedTreatment.getMemberId(), newDay, newDisease, newTreat, newCost);
                    treatmentService.updateTreatment(updatedTreatment);
                    System.out.println("치료 내용이 수정되었습니다.");
                    break;
                case 3:
                    idInput = getId(scanner, "치료내용을 삭제할 반려동물 또는 회원의 ID 입력(M_XXXXX/A_XXXXXX): ");
                    type = idInput.startsWith("M_") ? "Member" : "Animal";

                    treatments = treatmentService.findTreatmentsById(idInput, type);
                    if (treatments.isEmpty()) {
                        System.out.println("해당 ID로 조회된 치료 내역이 없습니다.");
                        break;
                    }
                    System.out.println("조회된 치료 내역:");
                    for (Treatment treatment : treatments) {
                        System.out.println("치료ID: " + treatment.getTreatId() + ", 날짜: " + treatment.getDay() + ", 병명: " + treatment.getDisease() + ", 조치: " + treatment.getTreat() + ", 비용: " + treatment.getCost());
                    }
                    selectedId = getId(scanner, "삭제할 치료 내역의 치료ID 입력: ");
                    selectedTreatment = treatments.stream()
                            .filter(t -> t.getTreatId().equals(selectedId))
                            .findFirst()
                            .orElse(null);

                    if (selectedTreatment == null) {
                        System.out.println("선택한 치료ID에 해당하는 내역이 없습니다.");
                        break;
                    }
                    treatmentService.cancelTreatment(selectedId);
                    System.out.println("치료 예약이 취소되었습니다.");
                    break;
                case 4:
                    idInput = getId(scanner, "치료내용을 조회할 반려동물 또는 회원의 ID 입력(M_XXXXX/A_XXXXXX): ");
                    type = idInput.startsWith("M_") ? "Member" : "Animal";

                    treatments = treatmentService.findTreatmentsById(idInput, type);
                    if (treatments.isEmpty()) {
                        System.out.println("해당 ID로 조회된 치료 내역이 없습니다.");
                        break;
                    }
                    System.out.println("조회된 치료 내역:");
                    for (Treatment treatment : treatments) {
                        System.out.println("치료ID: " + treatment.getTreatId() + ", 날짜: " + treatment.getDay() + ", 병명: " + treatment.getDisease() + ", 조치: " + treatment.getTreat() + ", 비용: " + treatment.getCost());
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
