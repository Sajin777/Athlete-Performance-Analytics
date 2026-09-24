package com.athlete.model;

public class Athlete {

    private int athleteId;
    private int squadId;
    private String firstName;
    private String lastName;
    private String position;

    public Athlete(
            int athleteId,
            int squadId,
            String firstName,
            String lastName,
            String position) {

        this.athleteId = athleteId;
        this.squadId = squadId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public int getSquadId() {
        return squadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
