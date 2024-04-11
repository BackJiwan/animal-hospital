package org.example.treatment.service;

import lombok.RequiredArgsConstructor;
import org.example.treatment.dto.TreatmentDto;
import org.example.treatment.entity.Treatment;
import org.example.treatment.repository.TreatmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public Treatment registerTreatment(TreatmentDto treatmentDto) {
        TreatmentDto savedTreatmentDto = treatmentRepository.save(treatmentDto);
        return Treatment.builder()
                .treatId(savedTreatmentDto.getTreatId())
                .animalId(savedTreatmentDto.getAnimalId())
                .memberId(savedTreatmentDto.getMemberId())
                .day(savedTreatmentDto.getDay())
                .disease(savedTreatmentDto.getDisease())
                .treat(savedTreatmentDto.getTreat())
                .cost(savedTreatmentDto.getCost())
                .build();
    }

    public Treatment updateTreatment(TreatmentDto treatmentDto) {
        TreatmentDto updatedTreatmentDto = treatmentRepository.update(treatmentDto);
        return Treatment.builder()
                .treatId(updatedTreatmentDto.getTreatId())
                .animalId(updatedTreatmentDto.getAnimalId())
                .memberId(updatedTreatmentDto.getMemberId())
                .day(updatedTreatmentDto.getDay())
                .disease(updatedTreatmentDto.getDisease())
                .treat(updatedTreatmentDto.getTreat())
                .cost(updatedTreatmentDto.getCost())
                .build();
    }

    public void cancelTreatment(String treatId) {
        treatmentRepository.delete(treatId);
    }

    public List<Treatment> findTreatmentsById(String id, String type) {
        List<TreatmentDto> treatmentDtos;
        if ("Member".equals(type)) {
            treatmentDtos = treatmentRepository.findByMemberId(id);
        } else {
            treatmentDtos = treatmentRepository.findByAnimalId(id);
        }

        return treatmentDtos.stream().map(dto -> Treatment.builder()
                .treatId(dto.getTreatId())
                .animalId(dto.getAnimalId())
                .memberId(dto.getMemberId())
                .day(dto.getDay())
                .disease(dto.getDisease())
                .treat(dto.getTreat())
                .cost(dto.getCost())
                .build()).collect(Collectors.toList());
    }
}
