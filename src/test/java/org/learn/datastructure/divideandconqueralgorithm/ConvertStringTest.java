package org.learn.datastructure.divideandconqueralgorithm;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertStringTest {
    @ParameterizedTest
    @MethodSource("provideEditDistanceTestCases")
    void testConvertString(String s1, String s2, int expectedOps) {
        assertEquals(expectedOps, ConvertString.convertString(s1, s2));
    }

    static Stream<Arguments> provideEditDistanceTestCases() {
        return Stream.of(
                Arguments.of("horse", "ros", 3),            // replace, remove, remove
                Arguments.of("intention", "execution", 5),  // complex edit
                Arguments.of("kitten", "sitting", 3),       // classic example
                Arguments.of("", "", 0),                    // both empty
                Arguments.of("abc", "", 3),                 // delete all
                Arguments.of("", "abc", 3),                 // insert all
                Arguments.of("table", "tbres", 3),          // insert all
                Arguments.of("abc", "abc", 0),              // no change
                Arguments.of("abcdef", "azced", 3),         // replace + insert/delete
                Arguments.of("a", "b", 1)                   // single character replaces
        );
    }
}