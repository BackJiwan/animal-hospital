package org.example.animal.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {
    private String animalId;
    private String memberId;
    private String name;
    private Date birth;
    private String species;
}
