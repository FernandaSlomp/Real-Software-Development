package org.example.cap01to03.interfaces;

import org.example.cap01to03.model.BankTransaction;
@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double acumulator, BankTransaction bankTransaction);

}
