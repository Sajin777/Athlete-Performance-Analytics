package com.athlete.model;

public class Metric {

    private int metricId;
    private String metricName;
    private String unit;

    public Metric(
            int metricId,
            String metricName,
            String unit) {

        this.metricId = metricId;
        this.metricName = metricName;
        this.unit = unit;
    }

    public int getMetricId() {
        return metricId;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getUnit() {
        return unit;
    }
}
