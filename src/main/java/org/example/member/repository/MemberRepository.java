package org.example.member.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.util.DataConnectionManager;
import org.example.member.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {
    public MemberDto findMemberById(String memberId) {
        String sql = "SELECT member_id, name, birth FROM members WHERE member_id = ?";

        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                MemberDto memberDto = new MemberDto();
                memberDto.setMemberId(rs.getString("member_id"));
                memberDto.setName(rs.getString("name"));
                memberDto.setBirth(rs.getDate("birth"));
                return memberDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MemberDto addMember(MemberDto memberDTO) {
        String sql = "INSERT INTO members (member_id, name, birth) VALUES (?, ?, ?)";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberDTO.getMemberId());
            pstmt.setString(2, memberDTO.getName());
            pstmt.setDate(3, new java.sql.Date(memberDTO.getBirth().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberDTO;
    }

    public void deleteMember(String memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
