package org.example.cap01to03;

import org.example.cap01to03.interfaces.BankTransactionFilter;
import org.example.cap01to03.model.BankTransaction;

import java.time.Month;

public class BankTransactionIsFebruaryAndExpensive implements BankTransactionFilter {
    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth().equals(Month.FEBRUARY)
                && bankTransaction.getAmount() >= 1_000;
    }
}
