package org.example.cap01to03.utils;

import org.example.cap01to03.interfaces.BankStatementParser;
import org.example.cap01to03.model.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    public BankStatementCSVParser() {
    }

    static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public BankTransaction parseFrom(String line){

        String[] colunas = line.split(",");
        LocalDate date = LocalDate.parse(colunas[0], formatterDate);
        double amount = Double.parseDouble(colunas[1]);
        String description = colunas[2];

        return new BankTransaction(date, amount, description);
    }
    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {

        final List<BankTransaction> bankTransactions = new ArrayList<>();

        for(final String line: lines){
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }

}
