package com.athlete.model;

public class User {

    private int userId;
    private int athleteId;
    private String username;
    private String role;
    private String status;

    public User(
            int userId,
            int athleteId,
            String username,
            String role,
            String status) {

        this.userId = userId;
        this.athleteId = athleteId;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
