package org.learn.datastructure.linkedlist;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircularDoublyLinkedListTest {

    @ParameterizedTest(name = "Insert {1} at index {2} in list {0} -> Expect: {3}")
    @MethodSource("insertionDataProvider")
    void testInsertAtLocation(CircularDoublyLinkedList<Integer> list, Integer data, int location, Object expected) {
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
                Arguments.of(createList(), 10, 0, "10 (circular)"),                      // Insert into an empty list
                Arguments.of(createList(10), 20, 0, "20 <-> 10 (circular)"),            // Insert at head (non-empty)
                Arguments.of(createList(10), 30, 1, "10 <-> 30 (circular)"),            // Insert at tail
                Arguments.of(createList(10, 20), 15, 1, "10 <-> 15 <-> 20 (circular)"), // Insert in the middle
                Arguments.of(createList(10, 20, 30), 25, 2, "10 <-> 20 <-> 25 <-> 30 (circular)"),
                Arguments.of(createList(10, 20, 30), 5, 0, "5 <-> 10 <-> 20 <-> 30 (circular)"),
                Arguments.of(createList(10, 20, 30), 35, 3, "10 <-> 20 <-> 30 <-> 35 (circular)"),
                Arguments.of(createList(10, 20, 30), 22, 1, "10 <-> 22 <-> 20 <-> 30 (circular)"),
                Arguments.of(createList(10, 20, 30), 99, 3, "10 <-> 20 <-> 30 <-> 99 (circular)"), // tail insert by size

                // Invalid insertions
                Arguments.of(createList(10, 20), 99, -1, IllegalArgumentException.class),  // Negative index
                Arguments.of(createList(10, 20), 99, 5, IllegalArgumentException.class),   // Index too large
                Arguments.of(createList(), 99, 1, IllegalArgumentException.class),        // Insert at 1 in an empty list
                Arguments.of(createList(10, 20), null, 1, IllegalArgumentException.class),      // Null data (optional)

                // Boundary test
                Arguments.of(createList(1, 2, 3, 4, 5), 100, 0, "100 <-> 1 <-> 2 <-> 3 <-> 4 <-> 5 (circular)") // boundary start
        );
    }

    @ParameterizedTest(name = "Delete at index {1} in list {0} -> Expect: {2}")
    @MethodSource("deletionDataProvider")
    void testDeleteAtLocation(CircularDoublyLinkedList<Integer> list, int location, Object expected) {
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
                Arguments.of(createList(10), 0, ""),
                Arguments.of(createList(10, 20), 0, "20 (circular)"),                    // delete head
                Arguments.of(createList(10, 20), 1, "10 (circular)"),                    // delete tail
                Arguments.of(createList(10, 20, 30), 1, "10 <-> 30 (circular)"),         // delete middle
                Arguments.of(createList(10, 20, 30, 40), 3, "10 <-> 20 <-> 30 (circular)"), // delete tail by size - 1

                // Invalid deletions
                Arguments.of(createList(), 0, IllegalStateException.class),             // delete it from empty
                Arguments.of(createList(10, 20), -1, IllegalArgumentException.class),   // negative index
                Arguments.of(createList(10, 20), 2, IllegalArgumentException.class),    // index out of bounds

                // Boundary deletions
                Arguments.of(createList(10, 20, 30, 40, 50), 0, "20 <-> 30 <-> 40 <-> 50 (circular)"),
                Arguments.of(createList(10, 20, 30, 40, 50), 4, "10 <-> 20 <-> 30 <-> 40 (circular)")
        );
    }


    private static CircularDoublyLinkedList<Integer> createList(Integer... values) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        for (Integer value : values) {
            list.insert(value);
        }
        return list;
    }

    public static void main(String[] args) {
        createList(10, 20, 30);
    }

}
