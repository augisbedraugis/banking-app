package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.service.BankUserDAO;
import com.bankapp.service.FundTransferDAO;
import com.bankapp.view.TransferView;
import javafx.scene.control.Alert;

public class TransferController {
    private TransferView transferView;

    public TransferController(TransferView transferView) {
        this.transferView = transferView;

        this.transferView.getTransferButton().setOnAction(e -> handleTransferFunds());
    }

    private void handleTransferFunds() {
        String recipientAccountIdText = transferView.getRecipientAccountField().getText();
        String amountText = transferView.getAmountField().getText();

        if (recipientAccountIdText.isEmpty() || amountText.isEmpty()) {
            showError("Please fill in both fields.");
            return;
        }

        try {
            int recipientAccountId = Integer.parseInt(recipientAccountIdText);
            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                showError("Amount must be greater than zero.");
                return;
            }

            Account senderAccount = transferView.getAccount();
            if (senderAccount.getBalance() < amount) {
                showError("Insufficient funds.");
                return;
            }

            FundTransferDAO transferDAO = new FundTransferDAO();
            System.out.println(senderAccount.getId() + "ID");
            System.out.println(recipientAccountId + "gavejo ID");
            System.out.println(amount + "sum");
            boolean success = transferDAO.transferFunds(senderAccount.getId(), recipientAccountId, amount);

            if (success) {
                showSuccess("Transfer successful!");
                BankUserDAO bankUserDAO = new BankUserDAO();
                Account updatedAccount = bankUserDAO.getAccountByUsername(senderAccount.getUsername());
                NavigationController.switchToDashboard(updatedAccount);

            } else {
                showError("Transfer failed...");
            }

        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter valid numbers.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
