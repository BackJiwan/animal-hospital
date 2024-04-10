package org.example.animal.repository;

import org.example.animal.dto.AnimalDto;
import org.example.util.DataConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalRepository {
    public AnimalDto findMemberById(String memberId) {
        String sql = "SELECT * FROM animal WHERE animal_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                AnimalDto animalDto = new AnimalDto();
                animalDto.setAnimalId(rs.getString("animal_id"));
                animalDto.setMemberId(rs.getString("member_id"));
                animalDto.setName(rs.getString("name"));
                animalDto.setBirth(rs.getDate("birth"));
                animalDto.setSpecies(rs.getString("species"));
                return animalDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public AnimalDto addAnimal(AnimalDto animalDto) {
        String sql = "INSERT INTO animal (ANIMAL_ID, MEMBER_ID, NAME, BIRTH, SPECIES)  VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animalDto.getAnimalId());
            pstmt.setString(2, animalDto.getMemberId());
            pstmt.setString(3, animalDto.getName());
            pstmt.setDate(4, new java.sql.Date(animalDto.getBirth().getTime()));
            pstmt.setString(5, animalDto.getSpecies());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalDto;
    }

    public void deleteAnimal(String animalId) {
        String sql = "DELETE FROM animal WHERE animal_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animalId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AnimalDto updateAnimal(AnimalDto animalDto) throws SQLException {
        String sql = "UPDATE animal SET name = ?, birth = ?, species = ? WHERE animal_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animalDto.getName());
            pstmt.setDate(2, new java.sql.Date(animalDto.getBirth().getTime()));
            pstmt.setString(3, animalDto.getSpecies());
            pstmt.setString(4, animalDto.getAnimalId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("회원 정보 업데이트 실패: 회원 ID를 찾을 수 없습니다.");
            }
            return animalDto;
        }
    }
}
