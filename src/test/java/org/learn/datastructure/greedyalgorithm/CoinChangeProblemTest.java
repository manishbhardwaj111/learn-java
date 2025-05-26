package org.learn.datastructure.greedyalgorithm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CoinChangeProblemTest {

    static Stream<TestCase> coinChangeProvider() {
        return Stream.of(
                new TestCase(new int[]{1, 2, 5}, 11, 3),     // 5+5+1
                new TestCase(new int[]{2}, 3, -1),           // Not possible
                new TestCase(new int[]{1}, 0, 0),            // No coins needed
                new TestCase(new int[]{1}, 2, 2),            // 1+1
                new TestCase(new int[]{1, 2, 5}, 100, 20),   // Optimal is 20 coins of 5
                new TestCase(new int[]{186, 419, 83, 408}, 6249, 20), // Large input
                new TestCase(new int[]{5, 10}, 3, -1),       // Impossible case
                new TestCase(new int[]{3, 7}, 9, 3)          // 3+3+3
        );
    }

    @ParameterizedTest
    @MethodSource("coinChangeProvider")
    @DisplayName("Test coinChange with various scenarios")
    void testCoinChange(TestCase testCase) {
        int actual = CoinChangeProblem.coinChangeProblem(testCase.coins(), testCase.amount());
        assertEquals(testCase.expected(), actual,
                "Failed for coins=" + java.util.Arrays.toString(testCase.coins()) +
                        ", amount=" + testCase.amount());
    }

    private record TestCase(int[] coins, int amount, int expected) {
        @Override
        public String toString() {
            return "TestCase{" +
                    "coins=" + Arrays.toString(coins) +
                    ", amount=" + amount +
                    ", expected=" + expected +
                    '}';
        }
    }
}
