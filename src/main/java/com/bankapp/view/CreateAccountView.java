package com.bankapp.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class CreateAccountView {
    private TextField nameField = new TextField();
    private TextField surnameField = new TextField();
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button registerButton = new Button("Register");
    private Button goBackButton = new Button("Go Back");

    public CreateAccountView() {
        nameField.setPromptText("Name");
        surnameField.setPromptText("Surname");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
    }

    public Scene getScene() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Surname:"), surnameField,
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                registerButton,
                goBackButton
        );

        return new Scene(layout, 300, 350);
    }

    public TextField getNameField() { return nameField; }
    public TextField getSurnameField() { return surnameField; }
    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getRegisterButton() { return registerButton; }
    public Button getGoBackButton() { return goBackButton; }
}
