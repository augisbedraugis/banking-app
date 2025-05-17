package com.bankapp.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class SettingsView {
    private Button changeUsernameButton = new Button("Change Username");
    private Button changePasswordButton = new Button("Change Password");
    private Button goBackButton = new Button("Go Back");
    private Scene scene;

    public SettingsView() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(changeUsernameButton, changePasswordButton, goBackButton);

        this.scene = new Scene(layout, 300, 150);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public Button getChangePasswordButton() {
        return changePasswordButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }
}