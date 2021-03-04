package com.example.book.chapter05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class InspectorTest {

    @Test
    void inspectOneConditionEvaluatesTrue() {
        // given
        JobTitleCondition jobTitleCondition = new JobTitleCondition();
        Inspector inspector = new Inspector(jobTitleCondition);
        Facts facts = new Facts();
        facts.addFact("jobTitle", "CTO");

        // when
        List<Report> reportList = inspector.inspect(facts);

        // then
        assertThat(1).isEqualTo(reportList.size());
        assertThat(reportList.get(0).isPositive()).isTrue();

    }

    private static class JobTitleCondition implements ConditionalAction {

        @Override
        public boolean evaluation(Facts facts) {
            return "CTO".equals(facts.getFact("jobTitle"));
        }

        @Override
        public void perform(Facts facts) {

        }
    }
}