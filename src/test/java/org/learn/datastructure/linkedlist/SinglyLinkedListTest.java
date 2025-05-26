package org.learn.datastructure.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setup() {
        list = new SinglyLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(50);
    }

    @ParameterizedTest(name = "Delete at position {0} should result in: {1}")
    @MethodSource("validDeletionProvider")
    void testValidDeletions(int position, String expectedList) {
        list.delete(position);
        assertEquals(expectedList, list.toString());
    }

    static Stream<Arguments> validDeletionProvider() {
        return Stream.of(
                Arguments.of(1, "20 -> 30 -> 40 -> 50"),  // delete it first
                Arguments.of(2, "10 -> 30 -> 40 -> 50"),  // delete middle
                Arguments.of(3, "10 -> 20 -> 40 -> 50")   // delete it last
        );
    }

    @ParameterizedTest(name = "Delete at invalid position {0} should throw: {1}")
    @CsvSource({
            "0, Position must be >= 1",
            "-1, Position must be >= 1",
            "6, Position must be inbound with size",
    })
    void testInvalidDeletions(int position, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> list.delete(position));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest(name = "rotate({0}) -> Expected: {1}")
    @MethodSource("rotationDataProvider")
    void testRotateList(int rotationCount, String expectedList) {
        list.rotate(rotationCount);
        assertEquals(expectedList, list.toString());
    }

    static Stream<Arguments> rotationDataProvider() {
        return Stream.of(
                Arguments.of(0, "10 -> 20 -> 30 -> 40 -> 50"),   // No rotation
                Arguments.of(1, "50 -> 10 -> 20 -> 30 -> 40"),   // Rotate by 1
                Arguments.of(2, "40 -> 50 -> 10 -> 20 -> 30"),   // Rotate by 2
                Arguments.of(5, "10 -> 20 -> 30 -> 40 -> 50"),   // Rotate by size = no change
                Arguments.of(7, "40 -> 50 -> 10 -> 20 -> 30")    // Rotate > size
        );
    }

    @ParameterizedTest(name = "rotate({1}) on list {0} -> Expected: {2}")
    @MethodSource("rotationDataProvider_uniqueLists")
    void testRotateListWithIndependentInstances(SinglyLinkedList<Integer> list, int rotationCount, String expectedList) {
        list.rotate(rotationCount);
        assertEquals(expectedList, list.toString());
    }

    static Stream<Arguments> rotationDataProvider_uniqueLists() {
        return Stream.of(
                Arguments.of(createList(10), 1, "10"),                       // Single-element list
                Arguments.of(createList(10, 20), 1, "20 -> 10"),             // Rotate by 1
                Arguments.of(createList(10, 20), 2, "10 -> 20")              // Rotate by list size
        );
    }

    private static SinglyLinkedList<Integer> createList(Integer... values) {
        var list = new SinglyLinkedList<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            list.insert(values[i]);
        }
        return list;
    }

}