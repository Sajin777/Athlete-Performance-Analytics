package com.athlete.service;

public class AnalyticsService {

    public double calculateWorkload(
            double duration,
            double rpe) {

        return duration * rpe;
    }

    public double calculateACWR(
            double acuteWorkload,
            double chronicWorkload) {

        if (chronicWorkload == 0) {
            return 0;
        }

        return acuteWorkload / chronicWorkload;
    }

    public String getWorkloadStatus(double acwr) {

        if (acwr > 1.5) {
            return "HIGH LOAD FLAG";
        } else if (acwr >= 0.8) {
            return "NORMAL";
        } else {
            return "LOW LOAD";
        }
    }
}
