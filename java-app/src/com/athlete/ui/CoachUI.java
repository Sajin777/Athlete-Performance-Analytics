package com.athlete.ui;

import com.athlete.dao.AthleteDAO;
import com.athlete.dao.AnalyticsDAO;

import java.util.Scanner;

public class CoachUI {

    public void showDashboard() {

        Scanner sc = new Scanner(System.in);

        AthleteDAO athleteDAO = new AthleteDAO();
        AnalyticsDAO analyticsDAO = new AnalyticsDAO();

        while (true) {

            System.out.println("\n===== COACH DASHBOARD =====");

            System.out.println("1. View Athletes");
            System.out.println("2. View Workload");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    athleteDAO.displayAthletes();
                    break;

                case 2:
                    analyticsDAO.displayWorkload();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
