package com.athlete.ui;

import com.athlete.dao.AnalyticsDAO;

public class AthleteUI {

    public void showDashboard() {

        AnalyticsDAO analyticsDAO =
                new AnalyticsDAO();

        System.out.println("\n===== ATHLETE DASHBOARD =====");

        analyticsDAO.displayWorkload();
    }
}
