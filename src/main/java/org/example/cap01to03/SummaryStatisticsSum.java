package org.example.cap01to03;

import org.example.cap01to03.interfaces.BankTransactionSummarizer;
import org.example.cap01to03.model.BankTransaction;

public class SummaryStatisticsSum implements BankTransactionSummarizer {

    @Override
    public double summarize(double acumulator, BankTransaction bankTransaction) {
        return bankTransaction.getAmount() + acumulator;
    }
}
