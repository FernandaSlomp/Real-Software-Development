package org.example.cap01to03.interfaces;

import org.example.cap01to03.model.BankTransaction;

import java.util.List;

public interface BankStatementParser {

    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);


}
