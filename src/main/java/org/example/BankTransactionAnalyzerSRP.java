package org.example;

import org.example.model.BankTransaction;
import org.example.utils.BankStatementCSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class BankTransactionAnalyzerSRP
{
    private static final String RESOURCES = "src/main/resources/";

    public static void main( String[] args ) throws IOException {

        // separando responsabilidades (SRP)

        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
        BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFrom(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));

    }

    private static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    private static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        return bankTransactions.stream()
                .filter(bankStatement -> month.equals(bankStatement.getDate().getMonth()))
                .collect(Collectors.toList());
    }
}
