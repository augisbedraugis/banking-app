//handles the logic of logging in. Should not require any more changes
package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.DashboardView;
import com.bankapp.view.LoginView;
import javafx.stage.Stage;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        loginView.getLoginButton().setOnAction(e -> {
            String user = loginView.getUserField().getText();
            String pass = loginView.getPassField().getText();

            Account account = AccountManager.getAccount(user, pass);
            if (account != null) {
                NavigationController.switchToDashboard(account);
            } else {
                NavigationController.showError("Invalid credentials");
            }
        });
    }
}
