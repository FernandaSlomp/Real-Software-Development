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

/**
 * Hello world!
 *
 */
public class BankTransactionAnalyzerSimple
{
    private static final String RESOURCES = "src/main/resources/";
    public static void main( String[] args ) throws IOException {

        // inicio nada indicado
        valorTotalTodosMeses();
        valorTotalJaneiro();

        // separando responsabilidades (SRP)

        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
        BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
        System.out.println("bankTransactions " + bankTransactions);

    }

    private static void valorTotalTodosMeses() throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        double valorTotal = 0;

        for(final String line: lines){
            String[] colunas = line.split(",");
            double amount = Double.parseDouble(colunas[1]);
            valorTotal += amount;
        }

        System.out.println("valorTotal " + valorTotal);

    }

    private static void valorTotalJaneiro() throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        double valorTotalJan = 0;

        for(final String line: lines){
            String[] colunas = line.split(",");

            LocalDate date = LocalDate.parse(colunas[0], formatterDate);

            if(date.getMonth() == Month.JANUARY){
                double amount = Double.parseDouble(colunas[1]);
                valorTotalJan += amount;
            }
        }

        System.out.println("valorTotalJan: " + valorTotalJan);
    }
}
