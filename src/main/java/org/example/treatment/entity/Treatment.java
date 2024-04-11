package org.example.treatment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Builder
@Getter
@ToString
public class Treatment {
    private String treatId;
    private String animalId;
    private String memberId;
    private Date day;
    private String disease;
    private String treat;
    private int cost;
}