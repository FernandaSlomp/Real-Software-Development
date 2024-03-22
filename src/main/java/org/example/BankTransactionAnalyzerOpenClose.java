package org.example;

import org.example.interfaces.BankTransactionFilter;
import org.example.interfaces.BankTransactionSummarizer;
import org.example.interfaces.Exporter;
import org.example.model.BankTransaction;
import org.example.model.SummaryStatistics;
import org.example.utils.BankStatementCSVParser;
import org.example.utils.HtmlExporter;

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


        // criação da interface Exporter -> Ela está alinhada com a ideia do open/close
        // Caso tenha que trocar para outro tipo de exportador
        System.out.println("Exports by html "
                + interfaceExporter(bankTransactions));


    }

    private static List<BankTransaction> functionalInterfaceLambda(final List<BankTransaction> bankTransactions) {

        BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        BankTransactionFilter filterByJanuary =
                bankTransaction -> bankTransaction.getDate().getMonth().equals( Month.JANUARY);
        return processor.findTransactions(filterByJanuary);

    }

    private static List<BankTransaction> functionalInterfaceImplementation(final List<BankTransaction> bankTransactions) {
        BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        BankTransactionIsFebruaryAndExpensive bankTransactionIsFebruaryAndExpensive
                = new BankTransactionIsFebruaryAndExpensive();
        return processor.findTransactions(bankTransactionIsFebruaryAndExpensive);

    }

    private static String interfaceExporter(final List<BankTransaction> bankTransactions) {
        BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
        SummaryStatistics sE = processor.summarizeTransactions();
        final Exporter exporter = new HtmlExporter();
        return exporter.export(sE);
    }
}
