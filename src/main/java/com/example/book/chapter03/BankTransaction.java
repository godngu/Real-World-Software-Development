package com.example.book.chapter03;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {

    private LocalDate date;
    private Double amount;
    private String description;

    public BankTransaction(LocalDate date, Double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
            "date=" + date +
            ", amount=" + amount +
            ", description='" + description + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankTransaction that = (BankTransaction) o;
        return Objects.equals(date, that.date) && Objects.equals(amount, that.amount)
            && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }
}
