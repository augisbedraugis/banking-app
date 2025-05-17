package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.LoginView;
import com.bankapp.service.BankUserDAO;
import javafx.scene.control.Alert;

public class LoginController {
    private LoginView loginView;
    private BankUserDAO bankUserDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.bankUserDAO = new BankUserDAO();
        setupHandlers();
    }

    private void setupHandlers() {
        loginView.getLoginButton().setOnAction(e -> handleLogin());
        loginView.getCreateAccountButton().setOnAction(e -> NavigationController.switchToCreateAccount());
    }

    private void handleLogin() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Both username and password are required.");
            return;
        }

        if (isValidLogin(username, password)) {
            Account account = bankUserDAO.getAccountByUsername(username);
            NavigationController.switchToDashboard(account);
        } else {
            showError("Invalid username or password.");
        }
    }


    private boolean isValidLogin(String username, String password) {
        return bankUserDAO.verifyCredentials(username, password);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
