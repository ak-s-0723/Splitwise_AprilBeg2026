package org.example.splitwise_april2026beg;

import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.models.Expense;
import org.example.splitwise_april2026beg.models.User;
import org.example.splitwise_april2026beg.models.UserExpense;
import org.example.splitwise_april2026beg.strategies.HeapBasedSettleUpStrategy;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[]) {
        HeapBasedSettleUpStrategy heapBasedSettleUpStrategy = new HeapBasedSettleUpStrategy();
        User user1 = new User();
        user1.setName("A");

        User user2 = new User();
        user2.setName("B");

        User user3 = new User();
        user3.setName("C");

        User user4 = new User();
        user4.setName("D");

        UserExpense userExpense1 = new UserExpense();
        userExpense1.setUser(user1);
        userExpense1.setAmount(1000D);

        UserExpense userExpense2 = new UserExpense();
        userExpense2.setUser(user1);
        userExpense2.setAmount(100D);

        UserExpense userExpense3 = new UserExpense();
        userExpense3.setUser(user2);
        userExpense3.setAmount(1500D);

        UserExpense userExpense4 = new UserExpense();
        userExpense4.setUser(user2);
        userExpense4.setAmount(120D);

        UserExpense userExpense5 = new UserExpense();
        userExpense5.setUser(user3);
        userExpense5.setAmount(1000D);

        UserExpense userExpense6 = new UserExpense();
        userExpense6.setUser(user3);
        userExpense6.setAmount(50D);

        UserExpense userExpense7 = new UserExpense();
        userExpense7.setUser(user1);
        userExpense7.setAmount(800D);

        UserExpense userExpense8 = new UserExpense();
        userExpense8.setUser(user1);
        userExpense8.setAmount(60D);

        UserExpense userExpense9 = new UserExpense();
        userExpense9.setUser(user2);
        userExpense9.setAmount(700D);

        UserExpense userExpense10 = new UserExpense();
        userExpense10.setUser(user2);
        userExpense10.setAmount(60D);

        UserExpense userExpense11 = new UserExpense();
        userExpense11.setUser(user4);
        userExpense11.setAmount(50D);

        List<UserExpense> whoHasPaid = new ArrayList<>();
        whoHasPaid.add(userExpense1);
        whoHasPaid.add(userExpense3);


        List<UserExpense> whoHasPaid2 = new ArrayList<>();
        whoHasPaid2.add(userExpense2);
        whoHasPaid2.add(userExpense4);

        List<UserExpense> whoHasToPay = new ArrayList<>();
        whoHasToPay.add(userExpense5);
        whoHasToPay.add(userExpense7);
        whoHasToPay.add(userExpense9);


        List<UserExpense> whoHasToPay2 = new ArrayList<>();
        whoHasToPay2.add(userExpense6);
        whoHasToPay2.add(userExpense10);
        whoHasToPay2.add(userExpense11);
        whoHasToPay2.add(userExpense8);


        Expense expense1 = new Expense();
        expense1.setWhoPaid(whoHasPaid);
        expense1.setWhoHasToPay(whoHasToPay);

        Expense expense2 = new Expense();
        expense2.setWhoPaid(whoHasPaid2);
        expense2.setWhoHasToPay(whoHasToPay2);

        List<Expense> expenses = new ArrayList<>();
        expenses.add(expense1);
        expenses.add(expense2);


        List<Transaction> transactions = heapBasedSettleUpStrategy.settleUp(expenses);
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getWhoHasToPay().getName()+" -> "+transaction.getToWhom().getName()+" : "+transaction.getAmount());
        }
    }
}
