package org.example.splitwise_april2026beg.models;

public class UserExpense {
    private User user;
    private Double amount;
    private UserExpenseType userExpenseType;
    private Expense expense;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UserExpenseType getUserExpenseType() {
        return userExpenseType;
    }

    public void setUserExpenseType(UserExpenseType userExpenseType) {
        this.userExpenseType = userExpenseType;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
