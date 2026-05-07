package org.example.splitwise_april2026beg.dtos;

import org.example.splitwise_april2026beg.models.User;

public class Transaction {
    private User from;
    private User to;
    private double amount;

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
