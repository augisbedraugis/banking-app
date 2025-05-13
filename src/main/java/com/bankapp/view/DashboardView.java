package com.bankapp.view;

import com.bankapp.model.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;

public class DashboardView {
    private Scene scene;
    private Button logoutButton = new Button("Log Out");

    public DashboardView(Account account) {
        Text welcomeText = new Text("Welcome, " + account.getFullName());
        Text balanceText = new Text("Balance: $" + account.getBalance());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(welcomeText, balanceText, logoutButton);

        this.scene = new Scene(layout, 300, 200);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
