package com.bankapp.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class LoginView {
    private TextField userField = new TextField();
    private PasswordField passField = new PasswordField();
    private Button loginButton = new Button("Log In");

    public Scene getScene() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Username:"), userField,
                new Label("Password:"), passField,
                loginButton
        );

        return new Scene(layout, 300, 200);
    }

    public TextField getUserField() { return userField; }
    public PasswordField getPassField() { return passField; }
    public Button getLoginButton() { return loginButton; }
}
