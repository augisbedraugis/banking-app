package com.bankapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTransferDB {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/accounts.db";
    private Connection transactionsConnection;

    public ConnectTransferDB() {
        try {
            transactionsConnection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to transactions database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return transactionsConnection;
    }

    public void closeConnection() {
        try {
            if (transactionsConnection != null && !transactionsConnection.isClosed()) {
                transactionsConnection.close();
                System.out.println("Transactions database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
