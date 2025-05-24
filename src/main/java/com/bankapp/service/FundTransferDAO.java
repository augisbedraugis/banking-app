package com.bankapp.service;

import com.bankapp.model.Transfer;

import java.sql.*;

public class FundTransferDAO {
    private ConnectTransferDB db = new ConnectTransferDB();
    private Connection connection;

    public FundTransferDAO() {
        connection = db.getConnection();
        setupDatabase();
    }

    public void setupDatabase() {
        String createTransfersTable = "CREATE TABLE IF NOT EXISTS transfers (" +
                "transferId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "senderAccountId INTEGER, " +
                "recipientAccountId INTEGER, " +
                "amount DECIMAL(10, 2), " +
                "date DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "description TEXT, " +
                "FOREIGN KEY (senderAccountId) REFERENCES bankUsers(id), " +
                "FOREIGN KEY (recipientAccountId) REFERENCES bankUsers(id)" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTransfersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean transferFunds(int senderAccountId, int recipientAccountId, double amount) {
        try {
            connection.setAutoCommit(false);

            double senderBalance = getAccountBalance(senderAccountId);

            if (senderBalance < amount) {
                return false;
            }

            updateAccountBalance(senderAccountId, senderBalance - amount);
            updateAccountBalance(recipientAccountId, getAccountBalance(recipientAccountId) + amount);

            String insertTransfer = "INSERT INTO transfers (senderAccountId, recipientAccountId, amount, description) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(insertTransfer)) {
                stmt.setInt(1, senderAccountId);
                stmt.setInt(2, recipientAccountId);
                stmt.setDouble(3, amount);
                stmt.setString(4, "Transfer from account " + senderAccountId + " to account " + recipientAccountId);
                stmt.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private double getAccountBalance(int accountId) {
        String query = "SELECT balance FROM bankUsers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private void updateAccountBalance(int accountId, double newBalance) {
        String updateQuery = "UPDATE bankUsers SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTransfer(Transfer transfer) {
        String insertTransfer = "INSERT INTO transfers (senderAccountId, amount, balanceAfterTransfer, description, recipientAccountId) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertTransfer)) {
            stmt.setInt(1, transfer.getSenderAccountId());
            stmt.setDouble(2, transfer.getAmount());
            stmt.setDouble(3, transfer.getBalanceAfterTransaction());
            stmt.setString(4, transfer.getDescription());
            stmt.setInt(5, transfer.getRecipientAccountId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeConnection() {
        db.closeConnection();
    }
}
