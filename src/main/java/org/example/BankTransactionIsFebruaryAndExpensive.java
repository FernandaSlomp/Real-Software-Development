package org.example;

import org.example.interfaces.BankTransactionFilter;
import org.example.model.BankTransaction;

import java.time.Month;

public class BankTransactionIsFebruaryAndExpensive implements BankTransactionFilter {
    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth().equals(Month.FEBRUARY)
                && bankTransaction.getAmount() >= 1_000;
    }
}
