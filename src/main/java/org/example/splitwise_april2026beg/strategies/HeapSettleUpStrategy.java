package org.example.splitwise_april2026beg.strategies;

import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.models.Expense;
import org.example.splitwise_april2026beg.models.User;
import org.example.splitwise_april2026beg.models.UserExpense;

import java.util.*;

import static java.lang.Math.abs;

public class HeapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> getProposedTransactionsToSettleUpExpenses(List<Expense> expenses) {
        Map<User,Double> expensesMap = new HashMap<>();

        for (Expense expense : expenses) {
            List<UserExpense> whoPaid = expense.getWhoPaid();
            List<UserExpense> whoHasToPay = expense.getWhoHasToPay();


            for (UserExpense userExpense : whoPaid) {
                User user = userExpense.getUser();
                if (expensesMap.containsKey(user)) {
                    Double currentAmount = expensesMap.get(user);
                    expensesMap.put(user, currentAmount + userExpense.getAmount());
                } else {
                    expensesMap.put(userExpense.getUser(), userExpense.getAmount());
                }
            }

            for (UserExpense userExpense : whoHasToPay) {
                User user = userExpense.getUser();
                if (expensesMap.containsKey(user)) {
                    Double currentAmount = expensesMap.get(user);
                    expensesMap.put(user, currentAmount - userExpense.getAmount());
                } else {
                    expensesMap.put(userExpense.getUser(), -userExpense.getAmount());
                }
            }
        }

        PriorityQueue<Map.Entry<User, Double>> positiveHeap =
                    new PriorityQueue<>(
                            (a, b) -> Double.compare(b.getValue(), a.getValue())
                    );

        PriorityQueue<Map.Entry<User, Double>> negativeHeap =
                    new PriorityQueue<>(
                            (a, b) -> Double.compare(a.getValue(), b.getValue())    //default logic
                    );

        for (Map.Entry<User, Double> entry : expensesMap.entrySet()) {
                if (entry.getValue() > 0) {
                    positiveHeap.add(entry);
                }
                else if (entry.getValue() < 0) {
                    negativeHeap.add(entry);
                }
        }

        List<Transaction> proposedTransactions = new ArrayList<>();
        while(!positiveHeap.isEmpty() && !negativeHeap.isEmpty()) {
                Map.Entry<User,Double> whoPaidMax = positiveHeap.poll();
                Map.Entry<User,Double> whoHasToPayMax = negativeHeap.poll();

                Transaction transaction = new Transaction();
                transaction.setFrom(whoHasToPayMax.getKey());
                transaction.setTo(whoPaidMax.getKey());

                Double amountToGet = whoPaidMax.getValue();
                Double amountToPay = abs(whoHasToPayMax.getValue());
                Double difference;
                if (amountToPay > amountToGet) {
                    difference = amountToPay - amountToGet;
                    whoHasToPayMax.setValue(-difference);
                    negativeHeap.add(whoHasToPayMax);
                    transaction.setAmount(amountToGet);
                } else if (amountToPay < amountToGet) {
                    difference = amountToGet - amountToPay;
                    whoPaidMax.setValue(difference);
                    positiveHeap.add(whoPaidMax);
                    transaction.setAmount(amountToPay);
                } else {
                    transaction.setAmount(amountToPay);
                }

                proposedTransactions.add(transaction);
        }

        return proposedTransactions;
    }
}


//Time Complexity
//O(n+ulogu)
//
//where:
//
//n = total user-expense entries
//u = total unique users