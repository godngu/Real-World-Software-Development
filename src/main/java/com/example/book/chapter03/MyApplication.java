package com.example.book.chapter03;

import java.io.IOException;

public class MyApplication {

    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
        final BankStatementParser parser = new BankStatementCSVParser();

        final Exporter exporter = new HtmlExporter();
        final String fileName = "/bank-data-simple.csv";
        analyzer.analyze(fileName, parser, exporter);
    }
}
