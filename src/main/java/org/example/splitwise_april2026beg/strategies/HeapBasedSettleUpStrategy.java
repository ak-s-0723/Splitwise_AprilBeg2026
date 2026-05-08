package org.example.splitwise_april2026beg.strategies;

import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.models.Expense;
import org.example.splitwise_april2026beg.models.User;
import org.example.splitwise_april2026beg.models.UserExpense;

import java.util.*;

import static java.lang.Math.abs;

public class HeapBasedSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        Map<User,Double> expensesMap = new HashMap<>();

        for(Expense expense : expenses) {
            List<UserExpense> whoPaid = expense.getWhoPaid();
            List<UserExpense> whoHasToPay = expense.getWhoHasToPay();

            for(UserExpense userExpense : whoPaid) {
                User user = userExpense.getUser();
                if(expensesMap.containsKey(user)) {
                    Double currentAmount = expensesMap.get(user);
                    expensesMap.put(user,currentAmount + userExpense.getAmount());
                } else {
                    expensesMap.put(user, userExpense.getAmount());
                }
            }

            for(UserExpense userExpense : whoHasToPay) {
                User user = userExpense.getUser();
                if(expensesMap.containsKey(user)) {
                    Double currentAmount = expensesMap.get(user);
                    expensesMap.put(user,currentAmount - userExpense.getAmount());
                } else {
                    expensesMap.put(user, -userExpense.getAmount());
                }
            }
        }

        PriorityQueue<Map.Entry<User,Double>> positiveHeap =
                new PriorityQueue<>(
                        (a,b) -> Double.compare(b.getValue(),a.getValue())
                );

        PriorityQueue<Map.Entry<User,Double>> negativeHeap =
                new PriorityQueue<>(
                        (a,b) -> Double.compare(a.getValue(),b.getValue())
                );

        for(Map.Entry<User,Double> entry : expensesMap.entrySet()) {
            if(entry.getValue() < 0) {
                negativeHeap.add(entry);
            }else {
                positiveHeap.add(entry);
            }
        }

        List<Transaction> proposedTransactions = new ArrayList<>();
        while(!positiveHeap.isEmpty() && !negativeHeap.isEmpty()) {
            Map.Entry<User,Double> whoPaidMax = positiveHeap.poll();
            Map.Entry<User,Double> whoHasToPayMax = negativeHeap.poll();
            Transaction transaction = new Transaction();
            transaction.setToWhom(whoPaidMax.getKey());
            transaction.setWhoHasToPay(whoHasToPayMax.getKey());

            Double amountToGet = whoPaidMax.getValue();
            Double amountToPay = abs(whoHasToPayMax.getValue());
            Double difference;
            if(amountToPay > amountToGet) {
                transaction.setAmount(amountToGet);
                difference = amountToPay - amountToGet;
                whoHasToPayMax.setValue(-difference);
                negativeHeap.add(whoHasToPayMax);
            } else if(amountToGet > amountToPay) {
                difference = amountToGet - amountToPay;
                whoPaidMax.setValue(difference);
                positiveHeap.add(whoPaidMax);
                transaction.setAmount(amountToPay);
            } else {
                // No need to add anyone
                transaction.setAmount(amountToPay);
            }
            proposedTransactions.add(transaction);
        }
        return proposedTransactions;
    }
}



/*

A,B,C

*** WHO HAS PAID       A : 1000 , B : 1500  , TOTAL 2500
WHO HAS TO PAY     A : 800 , B : 700 , C : 1000

*** WHO HAS PAID       A : 100 , B : 120  , TOTAL  : 220
WHO HAS TO PAY      A : 60 , B : 60 , C : 50 , D : 50

Step 1 : Find Net Amount to pay / to be paid for each user

A     -> 1100 - 860 (1000 + 100 - 800 - 60) = 240

B     -> 1620 - 760 = 860

C     -> 0 -  1050 = -1050

D     -> 0  - 50 = -50

{A,240},{B,860}
{C,-1050},{D,-50}

Step 2 : Put people into 1 of 2 buckets based on they have to get/ they will give

Positive Bucket  : {B,860}
                   {A,240}

Negative Bucket  : {C,-1050}
                   {D,-50}

-----------------------------------------

Positive Bucket  : {A,240}

Negative Bucket  : {C,-190}
                   {D,-50}

-----------------------------------------

Positive Bucket  : {A,50}

Negative Bucket  : {D,-50}


Step 3 :
          {B,800}

          {C,-1000}

          C -> B  : 800

          {B,0}
          {C,-200}

Positive Bucket {A,200}

Negative Bucket {C,-200}

         {A,200}
         {C,-200}

         C -> A : 200

         {A,0}
         {C,0}

 */
