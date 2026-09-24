package com.athlete.ui;

import com.athlete.dao.AthleteDAO;

public class AdminUI {

    public void showDashboard() {

        AthleteDAO athleteDAO =
                new AthleteDAO();

        System.out.println("\n===== ADMIN DASHBOARD =====");

        athleteDAO.displayAthletes();
    }
}
