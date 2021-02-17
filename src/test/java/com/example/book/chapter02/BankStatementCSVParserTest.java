package com.example.book.chapter02;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;

class BankStatementCSVParserTest {

    BankStatementParser parser = new BankStatementCSVParser();

    @Test
    void shouldParseOneCorrectLine() {
        // given
        String line = "30-01-2017,-50,Tesco";

        // when
        BankTransaction bankTransaction = parser.parseFrom(line);

        // then
        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50d, "Tesco");
        assertThat(bankTransaction.getDate()).isEqualTo(expected.getDate());
        assertThat(bankTransaction.getAmount()).isEqualTo(expected.getAmount());
        assertThat(bankTransaction.getDescription()).isEqualTo(expected.getDescription());
    }
}