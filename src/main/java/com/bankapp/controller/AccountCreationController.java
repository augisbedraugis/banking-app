package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.CreateAccountView;
import com.bankapp.service.BankUserDAO;
import javafx.scene.control.Alert;

public class AccountCreationController {
    private CreateAccountView createAccountView;
    private BankUserDAO bankUserDAO;

    public AccountCreationController(CreateAccountView createAccountView) {
        this.createAccountView = createAccountView;
        this.bankUserDAO = new BankUserDAO();
        setupHandlers();
    }

    private void setupHandlers() {
        createAccountView.getRegisterButton().setOnAction(e -> handleCreateAccount());
        createAccountView.getGoBackButton().setOnAction(e -> NavigationController.switchToLogin());
    }

    private void handleCreateAccount() {
        String fullName = createAccountView.getNameField().getText();
        String surname = createAccountView.getSurnameField().getText();
        String username = createAccountView.getUsernameField().getText();
        String password = createAccountView.getPasswordField().getText();

        if (fullName.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showError("All fields are required. Please fill in Full Name, Surname, Username, and Password.");
            return;
        }

        if (isUsernameTaken(username)) {
            showError("Username already exists. Please choose a different username.");
            return;
        }

        Account newAccount = new Account(fullName, surname, username, password);
        bankUserDAO.insertUser(newAccount);

        showSuccess("Account created successfully.");
        NavigationController.switchToLogin();
    }

    private boolean isUsernameTaken(String username) {
        return bankUserDAO.usernameExists(username);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
