package org.example.utils;

import org.example.model.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser {

    public BankStatementCSVParser() {
    }

    static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static BankTransaction parseFromCSV(String line){

        String[] colunas = line.split(",");
        LocalDate date = LocalDate.parse(colunas[0], formatterDate);
        double amount = Double.parseDouble(colunas[1]);
        String description = colunas[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromCSV(List<String> lines) {

        final List<BankTransaction> bankTransactions = new ArrayList<>();

        for(final String line: lines){
            bankTransactions.add(parseFromCSV(line));
        }

        return bankTransactions;
    }


}
