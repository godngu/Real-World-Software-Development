package com.example.book;

import com.example.book.chapter02.BankStatementAnalyzer;
import com.example.book.chapter02.BankStatementCSVParser;
import com.example.book.chapter02.BankStatementParser;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(BookApplication.class, args);

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        final String fileName = "/bank-data-simple.csv";
        bankStatementAnalyzer.analyze(fileName, bankStatementParser);
    }
}
