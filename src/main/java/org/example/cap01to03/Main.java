package org.example.cap01to03;

import org.example.cap01to03.interfaces.BankStatementParser;
import org.example.cap01to03.utils.BankStatementCSVParser;

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
