package com.example.book.chapter02;

import java.time.Month;
import java.util.List;

/**
 * 계산 연산 그룹화
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalInMonth(final Month month) {
        return bankTransactions.stream()
            .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
            .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }
}
