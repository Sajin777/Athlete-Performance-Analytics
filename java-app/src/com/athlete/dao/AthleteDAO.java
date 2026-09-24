package com.athlete.dao;

import com.athlete.DBConnection;
import java.sql.*;

public class AthleteDAO {

    public void displayAthletes() {

        String sql =
            "SELECT athlete_id, first_name, last_name, position " +
            "FROM athlete";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            System.out.println("\n--- ATHLETES ---");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("athlete_id")
                    + " | "
                    + rs.getString("first_name")
                    + " "
                    + rs.getString("last_name")
                    + " | "
                    + rs.getString("position")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
