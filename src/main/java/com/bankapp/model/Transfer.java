package com.bankapp.model;

public class Transfer {
    private int transferId;
    private int senderAccountId;
    private int recipientAccountId;
    private String transferType;
    private double amount;
    private double balanceAfterTransaction;
    private String description;

    public Transfer(int senderAccountId, String transferType, double amount,
                    double balanceAfterTransaction, String description) {
        this.senderAccountId = senderAccountId;
        this.transferType = transferType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.description = description;
    }

    public int getTransferId() { return transferId; }
    public int getSenderAccountId() { return senderAccountId; }
    public int getRecipientAccountId() { return recipientAccountId; }
    public String getTransferType() { return transferType; }
    public double getAmount() { return amount; }
    public double getBalanceAfterTransaction() { return balanceAfterTransaction; }
    public String getDescription() { return description; }

    public void setTransferId(int transferId) { this.transferId = transferId; }
    public void setSenderAccountId(int senderAccountId) { this.senderAccountId = senderAccountId; }
    public void setRecipientAccountId(int recipientAccountId) { this.recipientAccountId = recipientAccountId; }
    public void setTransferType(String transferType) { this.transferType = transferType; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setBalanceAfterTransaction(double balanceAfterTransaction) { this.balanceAfterTransaction = balanceAfterTransaction; }
    public void setDescription(String description) { this.description = description; }
}