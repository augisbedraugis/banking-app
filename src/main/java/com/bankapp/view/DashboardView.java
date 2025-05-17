package com.bankapp.view;
import com.bankapp.controller.DashboardController;
import javafx.scene.layout.HBox;
import com.bankapp.model.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class DashboardView {
    private Button settingsButton = new Button("Settings");
    private Scene scene;
    private Button logoutButton = new Button("Log Out");

    public DashboardView(Account account) {
        Text welcomeText = new Text("Welcome, " + account.getName());
        Text balanceText = new Text("Balance: $" + account.getBalance());

        VBox layout = new VBox(12);
        layout.setPadding(new Insets(10));
        HBox buttonRow = new HBox(40); // 10 = spacing between buttons
        buttonRow.getChildren().addAll(logoutButton, settingsButton);

        layout.getChildren().addAll(welcomeText, balanceText, buttonRow);

        this.scene = new Scene(layout, 300, 150);

        new DashboardController(this, account);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }
}
