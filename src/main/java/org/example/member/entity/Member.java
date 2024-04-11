package org.example.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Builder
@Getter
@ToString
public class Member {
    private String memberId;
    private String name;
    private Date birth;
}
