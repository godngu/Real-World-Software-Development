package com.example.book.chapter05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inspector {

    private final List<ConditionalAction> conditionalActions;

    public Inspector(ConditionalAction... conditionalActions) {
        this.conditionalActions = Arrays.asList(conditionalActions);
    }

    public List<Report> inspect(Facts facts) {
        List<Report> reportList = new ArrayList<>();

        for (ConditionalAction conditionalAction : conditionalActions) {
            boolean result = conditionalAction.evaluation(facts);
            reportList.add(new Report(conditionalAction, facts, result));
        }

        return reportList;
    }
}
