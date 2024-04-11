package org.example.treatment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDto {
    private String treatId;
    private String animalId;
    private String memberId;
    private Date day;
    private String disease;
    private String treat;
    private int cost;
}
