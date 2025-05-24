package com.bankapp;

import com.bankapp.service.FundTransferDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import com.bankapp.controller.NavigationController;
import com.bankapp.service.BankUserDAO;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BankUserDAO bankUserDAO = new BankUserDAO();
        FundTransferDAO fundTransferDAO = new FundTransferDAO();
        bankUserDAO.setupDatabase();
        fundTransferDAO.setupDatabase();

        NavigationController.init(primaryStage);

        NavigationController.switchToLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
