package org.example.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memberId;
    private String name;
    private Date birth; // java.sql.Date 사용
}
