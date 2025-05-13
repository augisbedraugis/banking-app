//should not need any changes
package com.bankapp.model;

public class Account {
    private String userId;       // Used as login username
    private String fullName;     // Display name
    private String password;     // Plaintext password
    private double balance;      // Account balance

    public Account(String userId, String fullName, String password, double balance) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
