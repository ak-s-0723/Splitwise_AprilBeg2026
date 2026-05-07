package org.example.splitwise_april2026beg.controllers;

import org.example.splitwise_april2026beg.dtos.ResponseStatus;
import org.example.splitwise_april2026beg.dtos.SettleUpGroupRequestDto;
import org.example.splitwise_april2026beg.dtos.SettleUpGroupResponseDto;
import org.example.splitwise_april2026beg.dtos.Transaction;
import org.example.splitwise_april2026beg.services.SettleUpService;

import java.util.List;

public class SettleUpController {
    private SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto settleUpGroupRequestDto) {
        SettleUpGroupResponseDto settleUpGroupResponseDto = new SettleUpGroupResponseDto();
        try {
            List<Transaction> transactionList = settleUpService.getProposedTransactionsToSettleUp(settleUpGroupRequestDto.getGroupId());
            settleUpGroupResponseDto.setTransactions(transactionList);
            settleUpGroupResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception exception) {
            settleUpGroupResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            settleUpGroupResponseDto.setFailureMessage(exception.getMessage());
        }
        return settleUpGroupResponseDto;
    }
}
