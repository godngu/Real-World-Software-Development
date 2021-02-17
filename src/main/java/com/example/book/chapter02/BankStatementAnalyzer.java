package com.example.book.chapter02;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources";
    private static final BankStatementCSVParser parser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final String fileName = "/bank-data-simple.csv";
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = parser.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());

        System.out.println("The total for transactions in January is " + bankStatementProcessor.calculateTotalInMonth(
            JANUARY));

        System.out.println("The total for transactions in February is " + bankStatementProcessor.calculateTotalInMonth(FEBRUARY));

        System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
