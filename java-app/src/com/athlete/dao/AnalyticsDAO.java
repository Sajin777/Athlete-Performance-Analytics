package com.athlete.dao;

import com.athlete.DBConnection;
import java.sql.*;

public class AnalyticsDAO {

    public void displayWorkload() {

        String sql =
            "SELECT athlete_name, " +
            "SUM(workload) total_workload " +
            "FROM athlete_workload_v " +
            "GROUP BY athlete_name";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            System.out.println("\n--- WORKLOAD ---");

            while (rs.next()) {

                System.out.println(
                    rs.getString("athlete_name")
                    + " : "
                    + rs.getDouble("total_workload")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
