package com.example.book.chapter05;

import java.util.HashMap;
import java.util.Map;

public class Facts {

    private final Map<String, String> facts = new HashMap<>();

    public String getFact(final String name) {
        return facts.get(name);
    }

    public void addFact(String name, String value) {
        facts.put(name, value);
    }
}
