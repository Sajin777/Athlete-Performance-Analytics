package com.athlete.model;

public class PerformanceRecord {

    private int recordId;
    private int sessionId;
    private int metricId;
    private double metricValue;

    public PerformanceRecord(
            int recordId,
            int sessionId,
            int metricId,
            double metricValue) {

        this.recordId = recordId;
        this.sessionId = sessionId;
        this.metricId = metricId;
        this.metricValue = metricValue;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getMetricId() {
        return metricId;
    }

    public double getMetricValue() {
        return metricValue;
    }
}
