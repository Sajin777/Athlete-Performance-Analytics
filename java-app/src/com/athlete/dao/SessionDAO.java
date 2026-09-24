package com.athlete.dao;

import com.athlete.DBConnection;
import java.sql.*;

public class SessionDAO {

    public void addSession(
            int athleteId,
            Date sessionDate,
            String type,
            double duration,
            double rpe,
            int heartRate,
            String notes) {

        String sql =
            "INSERT INTO training_session " +
            "(session_id, athlete_id, session_date, " +
            "session_type, duration_min, rpe, " +
            "avg_heart_rate, notes) " +
            "VALUES " +
            "(session_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement(sql)
        ) {

            ps.setInt(1, athleteId);
            ps.setDate(2, sessionDate);
            ps.setString(3, type);
            ps.setDouble(4, duration);
            ps.setDouble(5, rpe);
            ps.setInt(6, heartRate);
            ps.setString(7, notes);

            ps.executeUpdate();

            System.out.println(
                "Training session added successfully."
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
