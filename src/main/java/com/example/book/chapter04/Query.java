package com.example.book.chapter04;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

public class Query implements Predicate<Document> {

    private final Map<String, String> clauses;

    static Query parse(final String query) {
        return new Query(Arrays.stream(query.split(","))
            .map(str -> str.split(":"))
            .collect(toMap(x -> x[0], x -> x[1]))
        );
    }

    private Query(final Map<String, String> clauses) {
        this.clauses = clauses;
    }

    @Override
    public boolean test(Document document) {
        return false;
    }
}
