package com.athlete.model;

public class TrainingSession {

    private int sessionId;
    private int athleteId;
    private double duration;
    private double rpe;
    private int heartRate;
    private String sessionType;

    public TrainingSession(
            int sessionId,
            int athleteId,
            double duration,
            double rpe,
            int heartRate,
            String sessionType) {

        this.sessionId = sessionId;
        this.athleteId = athleteId;
        this.duration = duration;
        this.rpe = rpe;
        this.heartRate = heartRate;
        this.sessionType = sessionType;
    }

    public double getWorkload() {
        return duration * rpe;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public double getDuration() {
        return duration;
    }

    public double getRpe() {
        return rpe;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getSessionType() {
        return sessionType;
    }
}
