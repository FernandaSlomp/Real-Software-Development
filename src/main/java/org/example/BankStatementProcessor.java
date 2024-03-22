package org.example;

import org.example.interfaces.BankTransactionFilter;
import org.example.interfaces.BankTransactionSummarizer;
import org.example.model.BankTransaction;
import org.example.model.SummaryStatistics;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {

                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public List<BankTransaction> findMaxTransactionByDates(LocalDate start, LocalDate end) {

        List<BankTransaction> transactionByDates = transactionByDates(start,end);

        return transactionByDates.stream()
                .max(Comparator.comparing(BankTransaction::getAmount))
                .map(List::of)
                .orElse(List.of());
    }

    public List<BankTransaction> transactionByDates(LocalDate start, LocalDate end) {

        return bankTransactions.stream()
                .filter(bankStatement -> bankStatement.getDate().isAfter(start.minusDays(1))
                        && bankStatement.getDate().isBefore(end.plusDays(1)) )
                .collect(Collectors.toList());
    }

    public Map<Month, List<BankTransaction>> histogramTransaction() {

        return bankTransactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getDate().getMonth()));
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter filter) {
         List<BankTransaction> result = new ArrayList<>();
        for ( BankTransaction bankTransaction : bankTransactions){
            if (filter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }

        return result;
    }

    public double summarizeTransactions(BankTransactionSummarizer summarizer) {
        double result = 0;
        for ( BankTransaction bankTransaction : bankTransactions){
            result = summarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public SummaryStatistics summarizeTransactions() {

        final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getAverage());
    }
}