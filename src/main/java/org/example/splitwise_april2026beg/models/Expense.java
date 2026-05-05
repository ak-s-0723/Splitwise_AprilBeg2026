package org.example.splitwise_april2026beg.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Expense extends BaseModel {
    private Double amount;
    private Date time;
    private String description;
    private Group group;
    //private List<User> whoPaid;
    //private List<User> whoHasToPay
    private List<UserExpense> whoPaid;
    private List<UserExpense> whoHasToPay;
    private ExpenseType expenseType;
}
