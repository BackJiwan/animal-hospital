package org.example.treatment.repository;

import org.example.treatment.dto.TreatmentDto;
import org.example.util.DataConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentRepository {

    public TreatmentDto save(TreatmentDto treatmentDto) {
        String sql = "INSERT INTO treatment (treat_id, animal_id, member_id, day, disease, treat, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, treatmentDto.getTreatId());
            pstmt.setString(2, treatmentDto.getAnimalId());
            pstmt.setString(3, treatmentDto.getMemberId());
            pstmt.setDate(4, treatmentDto.getDay());
            pstmt.setString(5, treatmentDto.getDisease());
            pstmt.setString(6, treatmentDto.getTreat());
            pstmt.setInt(7, treatmentDto.getCost());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating treatment failed, no rows affected.");
            }

            return treatmentDto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TreatmentDto update(TreatmentDto treatmentDto) {
        String sql = "UPDATE treatment SET day = ?, disease = ?, treat = ?, cost = ? WHERE treat_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, treatmentDto.getDay());
            pstmt.setString(2, treatmentDto.getDisease());
            pstmt.setString(3, treatmentDto.getTreat());
            pstmt.setInt(4, treatmentDto.getCost());
            pstmt.setString(5, treatmentDto.getTreatId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating treatment failed, no rows affected.");
            }

            return treatmentDto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String treatId) {
        String sql = "DELETE FROM treatment WHERE treat_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, treatId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting treatment failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TreatmentDto> findByMemberId(String memberId) {
        List<TreatmentDto> treatments = new ArrayList<>();
        String sql = "SELECT * FROM treatment WHERE member_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                treatments.add(new TreatmentDto(
                        rs.getString("treat_id"),
                        rs.getString("animal_id"),
                        rs.getString("member_id"),
                        rs.getDate("day"),
                        rs.getString("disease"),
                        rs.getString("treat"),
                        rs.getInt("cost")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }

    public List<TreatmentDto> findByAnimalId(String animalId) {
        List<TreatmentDto> treatments = new ArrayList<>();
        String sql = "SELECT * FROM treatment WHERE animal_id = ?";
        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animalId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                treatments.add(new TreatmentDto(
                        rs.getString("treat_id"),
                        rs.getString("animal_id"),
                        rs.getString("member_id"),
                        rs.getDate("day"),
                        rs.getString("disease"),
                        rs.getString("treat"),
                        rs.getInt("cost")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treatments;
    }
}