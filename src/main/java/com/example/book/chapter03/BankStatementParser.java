package com.example.book.chapter03;

import java.util.List;

/**
 * 입출금 내역을 파싱하는 인터페이스 정의
 */
public interface BankStatementParser {

    BankTransaction parseFrom(String line);

    List<BankTransaction> parseLinesFrom(List<String> lines);
}
