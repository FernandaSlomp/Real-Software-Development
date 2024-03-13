package org.example;

import org.example.interfaces.BankStatementParser;
import org.example.model.BankTransaction;
import org.example.utils.BankStatementCSVParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BankTransactionCSVParserTest
{
    private BankStatementParser statementParser = new BankStatementCSVParser();
    private BankStatementProcessor statementprocessor;

    @Test
    public void shouldParseLine() throws Exception
    {

        String line = "30-01-2017,-50,Tesco";
        BankTransaction result = statementParser.parseFrom(line);

        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDescription(), result.getDescription());

    }

    @Test
    public void shouldErrorWhenParseLine() throws Exception
    {

        String line = "30-01-2017;-50;Tesco";

        try {
           statementParser.parseFrom(line);

        } catch (DateTimeParseException e){
            Assert.assertEquals(e.getMessage(),"Text '30-01-2017;-50;Tesco' could not be parsed, unparsed text found at index 10");
        }

    }

    @Test
    public void shouldCalculateTotalInJanuary() throws Exception
    {

        List<String> lines = new ArrayList<>();
        lines.add("30-01-2017,50,Tesco");

        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        statementprocessor = new BankStatementProcessor(result);

        double total = statementprocessor.calculateTotalInMonth(Month.JANUARY);
        double expected = 50;

        Assert.assertEquals(expected, total, 0);

    }

    @Test
    public void shouldShowTransactionByDates() throws Exception
    {

        List<String> lines = new ArrayList<>();
        lines.add("01-01-2022,50,Tesco");

        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        statementprocessor = new BankStatementProcessor(result);

        List<LocalDate> listaDias = new ArrayList<>();
        listaDias.add(LocalDate.of(2022, 1, 1));
        listaDias.add(LocalDate.of(2022, 1, 5));

        List<BankTransaction> transactionByDates = statementprocessor.transactionByDates(listaDias.get(0),listaDias.get(1));

        System.out.println(transactionByDates);

        BankTransaction expected = new BankTransaction(LocalDate.of(2022, Month.JANUARY, 1), 50, "Tesco");
        List<BankTransaction> bankTransactionlist= new ArrayList<>();
        bankTransactionlist.add(expected);

        Assert.assertEquals(bankTransactionlist, transactionByDates);

    }

    @Test
    public void shouldFindMaxTransactionByDates() throws Exception
    {

        List<String> lines = new ArrayList<>();
        lines.add("01-01-2022,50,Tesco");
        lines.add("02-01-2022,150,Tesco");
        lines.add("03-01-2022,250,Tesco");

        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        statementprocessor = new BankStatementProcessor(result);

        List<LocalDate> listaDias = new ArrayList<>();
        listaDias.add(LocalDate.of(2022, 1, 1));
        listaDias.add(LocalDate.of(2022, 1, 5));

        List<BankTransaction> transactionByDates = statementprocessor.findMaxTransactionByDates(listaDias.get(0),listaDias.get(1));

        System.out.println(transactionByDates);

        BankTransaction expected = new BankTransaction(LocalDate.of(2022, Month.JANUARY, 3), 250, "Tesco");
        List<BankTransaction> bankTransactionlist= new ArrayList<>();
        bankTransactionlist.add(expected);

        Assert.assertEquals(bankTransactionlist, transactionByDates);

    }

    @Test
    public void shouldReturnHistogramTransaction() throws Exception
    {

        List<String> lines = new ArrayList<>();
        lines.add("01-01-2022,50,Tesco");
        lines.add("02-01-2022,150,Tesco");
        lines.add("03-01-2022,250,Tesco");

        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        statementprocessor = new BankStatementProcessor(result);

        Map<Month, List<BankTransaction>> transactionByMonthResult = statementprocessor.histogramTransaction();

        System.out.println(transactionByMonthResult);

        BankTransaction transaction1 = new BankTransaction(LocalDate.of(2022, 1, 1), 50.0, "Tesco");
        BankTransaction transaction2 = new BankTransaction(LocalDate.of(2022, 1, 2), 150.0, "Tesco");
        BankTransaction transaction3 = new BankTransaction(LocalDate.of(2022, 1, 3), 250.0, "Tesco");

        Map<Month, List<BankTransaction>> transactionsByMonthExpected = new HashMap<>();
        transactionsByMonthExpected.put(Month.JANUARY, new ArrayList<>());
        transactionsByMonthExpected.get(Month.JANUARY).add(transaction1);
        transactionsByMonthExpected.get(Month.JANUARY).add(transaction2);
        transactionsByMonthExpected.get(Month.JANUARY).add(transaction3);

        Assert.assertEquals(transactionsByMonthExpected, transactionByMonthResult);

    }
}
