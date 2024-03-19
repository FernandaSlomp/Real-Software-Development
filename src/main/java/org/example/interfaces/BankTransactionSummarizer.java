package org.example.interfaces;

import org.example.model.BankTransaction;
@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double acumulator, BankTransaction bankTransaction);

}
