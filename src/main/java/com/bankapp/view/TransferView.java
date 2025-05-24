package com.bankapp.view;

import com.bankapp.controller.NavigationController;
import com.bankapp.controller.TransferController;
import com.bankapp.model.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class TransferView {
    private Account account;
    private TextField recipientAccountField = new TextField();
    private TextField amountField = new TextField();
    private Button transferButton = new Button("Transfer");
    private Button cancelButton = new Button("Cancel");
    private Scene scene;

    public TransferView(Account account) {
        this.account = account;

        VBox layout = new VBox(12);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Recipient Account ID:"), recipientAccountField,
                new Label("Amount to Transfer:"), amountField,
                transferButton, cancelButton
        );

        new TransferController(this);

        cancelButton.setOnAction(e -> NavigationController.switchToDashboard(account));

        this.scene = new Scene(layout, 300, 200);
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getRecipientAccountField() {
        return recipientAccountField;
    }

    public TextField getAmountField() {
        return amountField;
    }

    public Button getTransferButton() {
        return transferButton;
    }

    public Account getAccount() {
        return account;
    }

    public void closeWindow() {
        Stage stage = (Stage) transferButton.getScene().getWindow();
        stage.close();
    }
}