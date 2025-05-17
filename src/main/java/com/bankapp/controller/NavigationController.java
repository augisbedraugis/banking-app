//class used to handle scene/view switches
package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.LoginView;
import com.bankapp.view.DashboardView;
import com.bankapp.view.SettingsView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NavigationController {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }
    public static void switchToSettings(Account account) {
        SettingsView view = new SettingsView();

        // 🔁 Go back button returns to dashboard
        view.getGoBackButton().setOnAction(e -> {
            switchToDashboard(account);
        });

        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Settings");
    }
    public static void switchToLogin() {
        LoginView view = new LoginView();
        new LoginController(view);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
    public static void switchToSettings() {
        SettingsView view = new SettingsView();
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Settings");
    }

    public static void switchToDashboard(Account account) {
        DashboardView view = new DashboardView(account);
        view.getLogoutButton().setOnAction(e -> switchToLogin());
        view.getSettingsButton().setOnAction(e -> switchToSettings(account));
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Dashboard");
    }


    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
