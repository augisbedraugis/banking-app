package com.bankapp.service;

import com.bankapp.model.Account;

import java.sql.*;

public class BankUserDAO {
    private ConnectDB db = new ConnectDB();
    private Connection connection;

    public BankUserDAO() {
        connection = db.getConnection();
    }

    public void setupDatabase() {
        String createTable = "CREATE TABLE IF NOT EXISTS bankUsers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fullName TEXT, " +
                "surname TEXT, " +
                "userName TEXT, " +
                "password TEXT, " +
                "balance DECIMAL(10, 2) DEFAULT 1000.00" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(Account newAccount) {
        String insertUser = "INSERT INTO bankUsers (fullName, surname, userName, password, balance) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(insertUser)) {
            stmt.setString(1, newAccount.getName());
            stmt.setString(2, newAccount.getSurname());
            stmt.setString(3, newAccount.getUsername());
            stmt.setString(4, newAccount.getPassword());
            stmt.setDouble(5, newAccount.getBalance());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByUsername(String username) {
        String query = "SELECT * FROM bankUsers WHERE userName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getString("fullName"),
                        rs.getString("surname"),
                        rs.getString("userName"),
                        rs.getString("password")
                );
                account.setBalance(rs.getDouble("balance"));
                account.setId(rs.getInt("id"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyCredentials(String username, String password) {
        String query = "SELECT * FROM bankUsers WHERE userName = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePassword(Account account) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE bankUsers SET password = ? WHERE userName = ?")) {
            stmt.setString(1, account.getPassword());
            stmt.setString(2, account.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(Account account) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE bankUsers SET userName = ? WHERE id = ?")) {
            stmt.setString(1, account.getUsername());
            stmt.setInt(2, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username) {
        String checkQuery = "SELECT COUNT(*) FROM bankUsers WHERE userName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(checkQuery)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(int id) {
        String deleteUser = "DELETE FROM bankUsers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteUser)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
