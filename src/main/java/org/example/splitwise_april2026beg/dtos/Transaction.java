package org.example.splitwise_april2026beg.dtos;

import org.example.splitwise_april2026beg.models.User;

public class Transaction {
    private User whoHasToPay;
    private User ToWhom;
    private Double amount;

    public User getWhoHasToPay() {
        return whoHasToPay;
    }

    public void setWhoHasToPay(User whoHasToPay) {
        this.whoHasToPay = whoHasToPay;
    }

    public User getToWhom() {
        return ToWhom;
    }

    public void setToWhom(User toWhom) {
        ToWhom = toWhom;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
