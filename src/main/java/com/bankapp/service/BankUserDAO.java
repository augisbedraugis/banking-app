package com.bankapp.service;

import java.sql.*;

public class BankUserDAO {
    private ConnectDB db = new ConnectDB();
    private Connection connection;

    public BankUserDAO() {connection = db.getConnection();}

    public void setupDatabase() {
        String createTable = "CREATE TABLE IF NOT EXISTS bankUsers (id INTEGER PRIMARY KEY, name TEXT, password TEXT, balance DECIMAL(10, 2) DEFAULT 0.00)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTable);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(int id, String name, String password){
        String insertUser = "INSERT INTO bankUsers (id, name, password) VALUES (?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(insertUser)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateUserName(int id, String name) {
        String updateQuery = "UPDATE bankUsers SET name = ?  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(int id, String password) {
        String updateQuery = "UPDATE bankUsers SET password = ?  WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setString(1, password);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

