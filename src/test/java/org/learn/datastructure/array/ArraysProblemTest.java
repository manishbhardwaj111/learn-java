package org.learn.datastructure.array;

import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

public class ArraysProblemTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testFindPairs(int[] inputArray, int targetSum, List<List<Integer>> expectedPairs) {
        List<List<Integer>> actualPairs = ArraysProblem.findPairs(inputArray, targetSum);
        assertEquals(expectedPairs.size(), actualPairs.size(), "Size of pairs should match");
        assertEquals(expectedPairs, actualPairs);
    }

    private static List<Object[]> provideTestCases() {
        return List.of(
                new Object[]{
                        new int[]{2, 4, 3, 5, 7, 8, 1, 6},
                        9,
                        List.of(
                                List.of(4, 5),
                                List.of(2, 7),
                                List.of(8, 1),
                                List.of(3, 6)
                        )
                },
                new Object[]{
                        new int[]{1, 2, 3, 4, 5},
                        6,
                        List.of(
                                List.of(2, 4),
                                List.of(1, 5)
                        )
                },
                new Object[]{
                        new int[]{0, -1, 2, -3, 1},
                        -2,
                        List.of(
                                List.of(-3, 1)
                        )
                }
        );
    }
}
