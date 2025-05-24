package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.DashboardView;
import com.bankapp.view.TransferView;
import com.bankapp.view.SettingsView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DashboardController {
    private DashboardView dashboardView;
    private Account account;

    public DashboardController(DashboardView dashboardView, Account account) {
        this.dashboardView = dashboardView;
        this.account = account;

        this.dashboardView.getSettingsButton().setOnAction(this::handleSettingsButton);
        this.dashboardView.getLogoutButton().setOnAction(this::handleLogoutButton);

        this.dashboardView.getTransferFundsButton().setOnAction(this::handleTransferFunds);
    }

    private void handleSettingsButton(ActionEvent event) {
        NavigationController.switchToSettings(account);
    }

    private void handleLogoutButton(ActionEvent event) {
        NavigationController.switchToLogin();
    }

    private void handleTransferFunds(ActionEvent event) {
        TransferView transferView = new TransferView(account);
        Stage stage = (Stage) dashboardView.getScene().getWindow();
        stage.setScene(transferView.getScene());
        stage.setTitle("Transfer Funds");
        stage.show();
    }
}
