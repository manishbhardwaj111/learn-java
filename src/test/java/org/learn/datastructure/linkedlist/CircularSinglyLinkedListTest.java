package org.learn.datastructure.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircularSinglyLinkedListTest {

    @ParameterizedTest(name = "Insert {1} at index {2} in list {0} -> Expect: {3}")
    @MethodSource("insertionDataProvider")
    void testInsertAtLocation(CircularSinglyLinkedList<Integer> list, Integer data, int location, Object expected) {
        if (expected instanceof String expectedList) {
            list.insert(data, location);
            assertEquals(expectedList, list.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> list.insert(data, location));
        }
    }

    static Stream<Arguments> insertionDataProvider() {
        return Stream.of(
                // Valid insertions
                Arguments.of(createList(10, 30), 20, 1, "10 -> 20 -> 30 (circular)"),  // Insert in the middle
                Arguments.of(createList(10, 30), 5, 0, "5 -> 10 -> 30 (circular)"),    // Insert at beginning
                Arguments.of(createList(10, 30), 40, 2, "10 -> 30 -> 40 (circular)"),  // Insert at the end
                Arguments.of(createList(10, 30), 40, 3, "10 -> 30 -> 40 (circular)"),  // Insert at the end

                // Invalid insertions
                Arguments.of(createList(10, 20), 99, -1, IllegalArgumentException.class)
        );
    }

    @ParameterizedTest(name = "Delete at index {1} in list {0} -> Expect: {2}")
    @MethodSource("deletionDataProvider")
    void testDeleteAtLocation(CircularSinglyLinkedList<Integer> list, int location, Object expected) {
        if (expected instanceof String expectedList) {
            list.delete(location);
            assertEquals(expectedList, list.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> list.delete(location));
        }
    }

    static Stream<Arguments> deletionDataProvider() {
        return Stream.of(
                // Valid deletions
                Arguments.of(createList(10, 20, 30), 1, "20 -> 30 (circular)"),   // Delete head
                Arguments.of(createList(10, 20, 30), 2, "10 -> 30 (circular)"),   // Delete middle
                Arguments.of(createList(10, 20, 30), 3, "10 -> 20 (circular)"),   // Delete tail
                Arguments.of(createList(10), 1, ""),                              // Delete only element

                // Invalid deletions
                Arguments.of(createList(10, 20), -1, IllegalArgumentException.class),
                Arguments.of(createList(10, 20), 5, IllegalArgumentException.class)
        );
    }

    private static CircularSinglyLinkedList<Integer> createList(Integer... values) {
        CircularSinglyLinkedList<Integer> list = new CircularSinglyLinkedList<>();
        for (int i = 0; i < values.length; i++) {
            list.insert(values[i], i + 1);
        }
        return list;
    }

}