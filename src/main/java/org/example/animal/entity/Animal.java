package org.example.animal.entity;

import lombok.Builder;
import lombok.ToString;

import java.sql.Date;

@Builder
@ToString
public class Animal {
    private String animalId;
    private String memberId;
    private String name;
    private Date birth;
    private String species;
}
