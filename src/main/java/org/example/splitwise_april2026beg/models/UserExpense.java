package org.example.splitwise_april2026beg.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserExpense {
    private User user;
    private Double amount;
    private Expense expense;
    private UserExpenseType userExpenseType;
}
