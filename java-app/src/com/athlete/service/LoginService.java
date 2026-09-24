package com.athlete.service;

import com.athlete.DBConnection;
import com.athlete.model.User;

import java.sql.*;

public class LoginService {

    public User login(
            String username,
            String password) {

        String sql =
            "SELECT user_id, athlete_id, username, role, status " +
            "FROM app_user " +
            "WHERE username = ? " +
            "AND password_hash = ? " +
            "AND status = 'ACTIVE'";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                con.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new User(
                    rs.getInt("user_id"),
                    rs.getInt("athlete_id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
