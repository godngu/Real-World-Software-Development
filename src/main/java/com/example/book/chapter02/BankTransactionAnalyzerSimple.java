package com.example.book.chapter02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources";

    private static final BankStatementCSVParser parser = new BankStatementCSVParser();

    public static void main(String[] args) throws IOException {
        final String fileName = "/bank-data-simple.csv";
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = parser.parseLinesFromCSV(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    private static Double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream()
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    private static Double selectInMonth(List<BankTransaction> bankTransactions, Month january) {
        double total = 0d;
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == january) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }
}
