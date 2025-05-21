package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.CreateAccountView;
import com.bankapp.view.LoginView;
import com.bankapp.view.DashboardView;
import com.bankapp.view.SettingsView;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NavigationController {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void switchToLogin() {
        LoginView view = new LoginView();
        new LoginController(view);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void switchToDashboard(Account account) {
        DashboardView dashboardView = new DashboardView(account);
        // Hook up buttons
        dashboardView.getLogoutButton().setOnAction(e -> switchToLogin());
        dashboardView.getSettingsButton().setOnAction(e -> switchToSettings(account));

        primaryStage.setScene(dashboardView.getScene());
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }

    public static void switchToCreateAccount() {
        CreateAccountView createAccountView = new CreateAccountView();
        new AccountCreationController(createAccountView);
        primaryStage.setScene(createAccountView.getScene());
        primaryStage.setTitle("Create New Account");
    }

    public static void switchToSettings(Account account) {
        SettingsView view = new SettingsView();
        new SettingsController(view, account);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Settings");
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
