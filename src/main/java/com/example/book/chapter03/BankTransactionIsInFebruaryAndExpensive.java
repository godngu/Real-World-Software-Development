package com.example.book.chapter03;

import static java.time.Month.FEBRUARY;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {

    @Override
    public boolean test(BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == FEBRUARY
            && bankTransaction.getAmount() >= 1_000;
    }
}
