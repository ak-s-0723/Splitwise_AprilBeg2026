package org.example.splitwise_april2026beg.models;

import java.util.List;

public class Expense extends BaseModel {
    private String description;
    private Double totalAmountPaid; //total
    private Group group;
    private ExpenseType expenseType;
    private List<UserExpense> whoPaid;
    private List<UserExpense> whoHasToPay;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(Double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public List<UserExpense> getWhoPaid() {
        return whoPaid;
    }

    public void setWhoPaid(List<UserExpense> whoPaid) {
        this.whoPaid = whoPaid;
    }

    public List<UserExpense> getWhoHasToPay() {
        return whoHasToPay;
    }

    public void setWhoHasToPay(List<UserExpense> whoHasToPay) {
        this.whoHasToPay = whoHasToPay;
    }
}
