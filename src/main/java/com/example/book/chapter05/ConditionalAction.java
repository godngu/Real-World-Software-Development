package com.example.book.chapter05;

public interface ConditionalAction {

    boolean evaluation(Facts facts);

    void perform(Facts facts);
}
