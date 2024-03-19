package org.example;

import org.example.interfaces.BankTransactionFilter;
import org.example.model.BankTransaction;
import org.example.utils.BankStatementCSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class BankTransactionAnalyzerOpenClose
{
    private static final String RESOURCES = "src/main/resources/";

    public static void main( String[] args ) throws IOException {

        // criando interfaces (Open/Close)

        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
        BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFrom(lines);

        System.out.println("All transactions in january using Functional Interface lambda "
                + functionalInterfaceLambda(bankTransactions));

        System.out.println("All transactions in february and > 1000 using Functional Interface implements "
                + functionalInterfaceImplementation(bankTransactions));
    }

    private static List<BankTransaction> functionalInterfaceLambda(final List<BankTransaction> bankTransactions) {

        BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        BankTransactionFilter filterByJanuary =
                bankTransaction -> bankTransaction.getDate().getMonth().equals( Month.JANUARY);
        return processor.findTransactions(filterByJanuary);

    }

    private static List<BankTransaction> functionalInterfaceImplementation(final List<BankTransaction> bankTransactions) {
        BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        BankTransactionIsFebruaryAndExpensive bankTransactionIsFebruaryAndExpensive = new BankTransactionIsFebruaryAndExpensive();
        return processor.findTransactions(bankTransactionIsFebruaryAndExpensive);

    }

}
