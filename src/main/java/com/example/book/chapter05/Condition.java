package com.example.book.chapter05;

@FunctionalInterface
public interface Condition {

    boolean evaluate(Facts facts);
}
