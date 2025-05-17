package com.bankapp.controller;

import com.bankapp.model.Account;
import com.bankapp.view.DashboardView;
import com.bankapp.view.SettingsView;
import javafx.event.ActionEvent;

public class DashboardController {
    private DashboardView dashboardView;
    private Account account;

    public DashboardController(DashboardView dashboardView, Account account) {
        this.dashboardView = dashboardView;
        this.account = account;

        this.dashboardView.getSettingsButton().setOnAction(this::handleSettingsButton);
        this.dashboardView.getLogoutButton().setOnAction(this::handleLogoutButton);
    }

    private void handleSettingsButton(ActionEvent event) {
        NavigationController.switchToSettings(account);
    }

    private void handleLogoutButton(ActionEvent event) {
        NavigationController.switchToLogin();
    }
}
