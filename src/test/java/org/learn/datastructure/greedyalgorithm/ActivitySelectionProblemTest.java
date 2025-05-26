package org.learn.datastructure.greedyalgorithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ActivitySelectionProblemTest {

    private static Stream<TestCase> provideActivities() {
        return Stream.of(
                // 🟢 Normal valid case
                new TestCase(
                        List.of(
                                new ActivitySelectionProblem.Activity("A1", 1, 3),
                                new ActivitySelectionProblem.Activity("A2", 3, 4),
                                new ActivitySelectionProblem.Activity("A3", 0, 6),
                                new ActivitySelectionProblem.Activity("A4", 5, 7),
                                new ActivitySelectionProblem.Activity("A5", 8, 9),
                                new ActivitySelectionProblem.Activity("A6", 5, 9)
                        ),
                        List.of("A1", "A2", "A4", "A5")
                ),
                // 🔴 All overlapping
                new TestCase(
                        List.of(
                                new ActivitySelectionProblem.Activity("B1", 1, 5),
                                new ActivitySelectionProblem.Activity("B2", 2, 6),
                                new ActivitySelectionProblem.Activity("B3", 3, 7)
                        ),
                        List.of("B1")
                ),
                // ⚪️ Edge case: Empty list
                new TestCase(
                        List.of(),
                        List.of()
                ),
                // ⚫ Null input
                new TestCase(
                        null,
                        List.of()
                ),
                // 🟠 Single activity
                new TestCase(
                        List.of(
                                new ActivitySelectionProblem.Activity("S1", 1, 2)
                        ),
                        List.of("S1")
                ),
                // 🔵 Activities with same end time
                new TestCase(
                        List.of(
                                new ActivitySelectionProblem.Activity("C1", 0, 5),
                                new ActivitySelectionProblem.Activity("C2", 1, 5),
                                new ActivitySelectionProblem.Activity("C3", 2, 5)
                        ),
                        List.of("C1")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideActivities")
    @DisplayName("Test maxActivity with multiple scenarios")
    void testMaxActivity(TestCase testCase) {
        List<ActivitySelectionProblem.Activity> result =
                ActivitySelectionProblem.maxActivity(testCase.input());

        List<String> actualNames = result.stream().map(ActivitySelectionProblem.Activity::name).toList();
        assertEquals(testCase.expectedNames(), actualNames);
    }

    private record TestCase(
            List<ActivitySelectionProblem.Activity> input,
            List<String> expectedNames
    ) {}
}
