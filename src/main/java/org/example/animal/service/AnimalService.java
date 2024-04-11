package org.example.animal.service;

import lombok.RequiredArgsConstructor;
import org.example.animal.repository.AnimalRepository;
import org.example.animal.dto.AnimalDto;
import org.example.animal.entity.Animal;

@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

//    public MemberService(MemberRepository animalRepository) {
//        this.animalRepository = animalRepository;
//    }

    public Animal registerAnimal(AnimalDto animalDto) {
        // 여기서 중복 동물 검증 등의 로직을 추가할 수 있음
        animalRepository.addAnimal(animalDto);
        return Animal.builder()
                .animalId(animalDto.getAnimalId())
                .memberId(animalDto.getMemberId())
                .name(animalDto.getName())
                .birth(animalDto.getBirth())
                .species(animalDto.getSpecies())
                .build();
    }

    public Animal findAnimalById(String animalId) {
        AnimalDto animal = animalRepository.findMemberById(animalId);
        return Animal.builder()
                .animalId(animal.getAnimalId())
                .memberId(animal.getMemberId())
                .name(animal.getName())
                .birth(animal.getBirth())
                .species(animal.getSpecies())
                .build();
    }

    public Animal updateAnimal(AnimalDto animalDto) {
        // 동물 존재 여부 검증
        AnimalDto existingMember = animalRepository.findMemberById(animalDto.getAnimalId());
        if (existingMember == null) {
            System.out.println("해당 ID의 동물이 존재하지 않습니다.");
            return null;
        }

        // 동물 정보 업데이트
        try {
            animalRepository.updateAnimal(animalDto);
            System.out.println("동물 정보가 성공적으로 업데이트 되었습니다.");
            return Animal.builder()
                    .animalId(animalDto.getAnimalId())
                    .memberId(animalDto.getMemberId())
                    .name(animalDto.getName())
                    .birth(animalDto.getBirth())
                    .species(animalDto.getSpecies())
                    .build();
        } catch (Exception e) {
            System.out.println("회원 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return null;
    }

    public void deleteAnimal(String AnimalId) {
        animalRepository.deleteAnimal(AnimalId);
    }
}
