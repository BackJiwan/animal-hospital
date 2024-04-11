package org.example.treatment.chartinfo;

import org.example.treatment.entity.Treatment;
import org.example.util.DataConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TreatmentInfo {
    public void viewChartInfo (List<Treatment> treatments, int num) {
        String sql = "SELECT T.treat_id, T.animal_id, T.member_id, T.day, A.name, A.birth, A.species, T.disease, T.treat, T.cost\n" +
                "FROM treatment T JOIN animal A ON T.animal_id = A.animal_id WHERE T.treat_id = ?";
        num--;
        Treatment treatment = treatments.get(num);
        String treatId = treatment.getTreatId();

        try (Connection conn = DataConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, treatId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("member_id") == null) {
                    System.out.println("해당 치료기록이 온전하지 않습니다.");
                    break;
                }
                System.out.println("==========================================");
                System.out.println("진료ID : " + rs.getString("treat_id"));
                System.out.println("동물ID : " + rs.getString("animal_id"));
                System.out.println("주인ID : " + rs.getString("member_id"));
                System.out.println("진료날짜 : " + rs.getDate("day"));
                System.out.println("동물 이름 : " + rs.getString("name"));
                System.out.println("동물 생일 : " + rs.getDate("birth"));
                System.out.println("동물 종 : " + rs.getString("species"));
                System.out.println("질병내용 : " + rs.getString("disease"));
                System.out.println("조치내역 : " + rs.getString("treat"));
                System.out.println("비용 : " + rs.getInt("cost"));
                System.out.println("==========================================");
            }

        } catch (SQLException e) {
            System.out.println("SQL 상태: " + e.getSQLState());
            System.out.println("에러 코드: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
