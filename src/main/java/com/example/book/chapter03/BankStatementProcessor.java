package com.example.book.chapter03;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * 계산 연산 그룹화
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions() {
        DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
            .mapToDouble(BankTransaction::getAmount)
            .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
            doubleSummaryStatistics.getMax(),
            doubleSummaryStatistics.getMin(),
            doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((accumulator, bankTransaction) ->
            bankTransaction.getDate().getMonth() == month
                ? accumulator + bankTransaction.getAmount() : accumulator);
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final BankTransactionFilter bankTransactionFilter) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= 1_000);
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
            .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
            .mapToDouble(BankTransaction::getAmount)
            .sum();
    }
}
