package org.example.splitwise_april2026beg.services;

import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.models.Expense;
import org.example.splitwise_april2026beg.models.Group;
import org.example.splitwise_april2026beg.repos.GroupRepo;
import org.example.splitwise_april2026beg.strategies.HeapBasedSettleUpStrategy;
import org.example.splitwise_april2026beg.strategies.SettleUpStrategy;

import java.util.List;
import java.util.Optional;

public class SettleUpService {

    private GroupRepo groupRepo;

    private SettleUpStrategy settleUpStrategy;

    public SettleUpService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
        this.settleUpStrategy = new HeapBasedSettleUpStrategy();
    }

    public List<Transaction> getProposedTransactionsToSettleUpGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepo.findGroupById(groupId);
        if (groupOptional.isEmpty()) {
           throw new RuntimeException("GroupId is invalid");
        }

        Group group = groupOptional.get();
        List<Expense> groupExpenses = group.getExpenses();
        return settleUpStrategy.settleUp(groupExpenses);
    }
}
