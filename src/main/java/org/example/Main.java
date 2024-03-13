package org.example;

import org.example.interfaces.BankStatementParser;
import org.example.utils.BankStatementCSVParser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws IOException {

        final BankStatementAnalyzerCoupling bankStatementAnalyzer
                = new BankStatementAnalyzerCoupling();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);

    }
}
