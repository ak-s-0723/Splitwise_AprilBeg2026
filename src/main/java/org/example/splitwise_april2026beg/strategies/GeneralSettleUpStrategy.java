package org.example.splitwise_april2026beg.strategies;

import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.models.Expense;

import java.util.List;

public class GeneralSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> getProposedTransactionsToSettleUpExpenses(List<Expense> expenses) {
        return null;
    }
}
