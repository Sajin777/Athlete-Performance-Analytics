package com.athlete.dao;

import com.athlete.DBConnection;
import java.sql.*;

public class PerformanceDAO {

    public void addPerformance(
            int sessionId,
            int metricId,
            double value) {

        String sql =
            "INSERT INTO performance_record " +
            "(record_id, session_id, metric_id, metric_value) " +
            "VALUES (performance_seq.NEXTVAL, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement(sql)
        ) {

            ps.setInt(1, sessionId);
            ps.setInt(2, metricId);
            ps.setDouble(3, value);

            ps.executeUpdate();

            System.out.println(
                "Performance record added."
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
