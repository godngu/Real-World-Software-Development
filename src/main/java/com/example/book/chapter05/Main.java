package com.example.book.chapter05;

public class Main {

    public static void main(String[] args) {
        var env = new Facts();
        env.addFact("name", "kil");
        env.addFact("jobTitle", "CTO");

        var businessRuleEngine = new BusinessRuleEngine(env);

        var rule = RuleBuilder
            .when(facts -> "CTO".equals(facts.getFact("jotTitle")))
            .then(facts -> {
                final var name = facts.getFact("name");
                System.out.println("name = " + name);
            });

        businessRuleEngine.addRule(rule);
        businessRuleEngine.run();
    }
}
