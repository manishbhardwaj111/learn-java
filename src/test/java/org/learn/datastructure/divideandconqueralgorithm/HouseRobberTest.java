package org.learn.datastructure.divideandconqueralgorithm;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HouseRobberTest {

    @ParameterizedTest
    @MethodSource("houseRobberTestCases")
    void testRob(int[] houses, int expected) {
        assertEquals(expected, HouseRobber.rob(houses));
    }

    static Stream<Arguments> houseRobberTestCases() {
        return Stream.of(
                Arguments.of(new int[]{}, 0),                      // Empty input
                Arguments.of(new int[]{5}, 5),                     // One house
                Arguments.of(new int[]{2, 1}, 2),                  // Two houses
                Arguments.of(new int[]{2, 7, 9, 3, 1}, 12),        // Classic case
                Arguments.of(new int[]{1, 2, 3, 1}, 4),            // Alternating better
                Arguments.of(new int[]{6, 7, 1, 30, 8, 2, 4}, 41), // High value in the middle
                Arguments.of(new int[]{5, 5, 10, 100, 10, 5}, 110),// Skipping for max
                Arguments.of(new int[]{20, 30, 50, 10, 5}, 75),    // Greedy trap
                Arguments.of(new int[]{100, 1, 1, 100}, 200)       // Both ends are max
        );
    }
}
