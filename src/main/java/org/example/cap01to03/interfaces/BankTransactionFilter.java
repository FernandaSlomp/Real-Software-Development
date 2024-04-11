package org.example.cap01to03.interfaces;

import org.example.cap01to03.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
