package com.example.book.chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankTransactionAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources";

    private static final BankStatementCSVParser parser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final String fileName = "/bank-data-simple.csv";
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream()
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    private static List<BankTransaction> selectInMonth(List<BankTransaction> bankTransactions, Month january) {
        return bankTransactions.stream()
            .filter(o -> o.getDate().getMonth() == january)
            .collect(Collectors.toList());
    }
}
