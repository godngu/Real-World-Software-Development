package com.example.book.chapter05;

public class RuleBuilder {

    private final Condition condition;

    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    public static RuleBuilder when(Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(Action action) {
        return new DefaultRule(condition, action);
    }
}
