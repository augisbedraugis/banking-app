package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.service.BankUserDAO;
import com.bankapp.view.SettingsView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class SettingsController {
    private SettingsView view;
    private Account account;

    public SettingsController(SettingsView view, Account account) {
        this.view = view;
        this.account = account;
        setupHandlers();
    }

    private void setupHandlers() {
        view.getChangePasswordButton().setOnAction(e -> handleChangePassword());
        view.getChangeUsernameButton().setOnAction(e -> handleChangeUsername());
        view.getGoBackButton().setOnAction(e -> NavigationController.switchToDashboard(account));
    }

    private void handleChangePassword() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        PasswordField oldPassField = new PasswordField();
        oldPassField.setPromptText("Old Password");

        PasswordField newPassField = new PasswordField();
        newPassField.setPromptText("New Password");

        PasswordField confirmPassField = new PasswordField();
        confirmPassField.setPromptText("Confirm New Password");

        Button updateBtn = new Button("Update Password");

        layout.getChildren().addAll(
                new Label("Change Password"),
                oldPassField,
                newPassField,
                confirmPassField,
                updateBtn
        );

        updateBtn.setOnAction(e -> {
            String oldP = oldPassField.getText();
            String newP = newPassField.getText();
            String confirmP = confirmPassField.getText();

            if (!account.getPassword().equals(oldP)) {
                showAlert("Old password is incorrect.");
                return;
            }

            if (!newP.equals(confirmP)) {
                showAlert("Passwords do not match.");
                return;
            }

            if (newP.isEmpty()) {
                showAlert("New password cannot be empty.");
                return;
            }

            BankUserDAO dao = new BankUserDAO();
            account.setPassword(newP);
            dao.updatePassword(account);
            showAlert("Password updated.");
            stage.close();
        });

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Change Password");
        stage.show();
    }

    private void handleChangeUsername() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField usernameField = new TextField(account.getUsername());
        usernameField.setPromptText("New username");

        Button saveButton = new Button("Save");

        layout.getChildren().addAll(
                new Label("Change Username"),
                usernameField,
                saveButton
        );

        saveButton.setOnAction(e -> {
            String newUsername = usernameField.getText().trim();

            if (newUsername.isEmpty()) {
                showAlert("Username cannot be empty.");
                return;
            }

            if (newUsername.equals(account.getUsername())) {
                showAlert("No changes made.");
                return;
            }

            BankUserDAO dao = new BankUserDAO();

            if (dao.usernameExists(newUsername)) {
                showAlert("Username already exists.");
                return;
            }

            account.setUsername(newUsername);
            dao.updateUsername(account);  // You'll add this method if missing
            showAlert("Username updated.");
            stage.close();
        });

        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Change Username");
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
