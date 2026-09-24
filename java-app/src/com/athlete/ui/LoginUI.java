package com.athlete.ui;

import com.athlete.model.User;
import com.athlete.service.LoginService;

import java.util.Scanner;

public class LoginUI {

    public User showLogin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n==============================");
        System.out.println(" ATHLETE PERFORMANCE SYSTEM");
        System.out.println("==============================");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        LoginService service = new LoginService();

        return service.login(username, password);
    }
}
