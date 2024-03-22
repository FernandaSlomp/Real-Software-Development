package org.example;

import org.example.interfaces.BankTransactionFilter;
import org.example.interfaces.BankTransactionSummarizer;
import org.example.model.BankTransaction;

import java.time.Month;

public class SummaryStatisticsSum implements BankTransactionSummarizer {

    @Override
    public double summarize(double acumulator, BankTransaction bankTransaction) {
        return bankTransaction.getAmount() + acumulator;
    }
}
