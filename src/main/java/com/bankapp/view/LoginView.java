package com.bankapp.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class LoginView {
    private TextField userField = new TextField();
    private PasswordField passField = new PasswordField();
    private Button loginButton = new Button("Log In");
    private Button createAccountButton = new Button("Create New Account");

    public Scene getScene() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Username:"), userField,
                new Label("Password:"), passField,
                loginButton,
                createAccountButton
        );

        return new Scene(layout, 300, 250);
    }

    public TextField getUsernameField() { return userField; }
    public PasswordField getPasswordField() { return passField; }
    public Button getLoginButton() { return loginButton; }
    public Button getCreateAccountButton() { return createAccountButton; }
}
