package org.learn.datastructure.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DoubleLinkedListTest {

    @ParameterizedTest(name = "Insert {1} at index {2} in list {0} -> Expect: {3}")
    @MethodSource("insertionDataProvider")
    void testInsertAtLocation(DoublyLinkedList<Integer> list, Integer data, int location, Object expected) {
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
                Arguments.of(createList(10, 30), 5, 0, "5 <-> 10 <-> 30"),    // Insert at beginning
                Arguments.of(createList(10, 30), 20, 1, "10 <-> 20 <-> 30"),  // Insert in the middle
                Arguments.of(createList(10, 20, 40), 30, 2, "10 <-> 20 <-> 30 <-> 40"),  // Insert at the end
                Arguments.of(createList(), 10, 0, "10"),                      // Insert in an empty list

                // Invalid insertions
                Arguments.of(createList(10, 20), 99, -1, IllegalArgumentException.class),  // Negative index
                Arguments.of(createList(10, 20), 99, 5, IllegalArgumentException.class)    // Index out of bounds
        );
    }

    @ParameterizedTest(name = "Insert {0} -> Reverse Traversal Expected: {1}")
    @MethodSource("reverseTraversalDataProvider")
    void testReverseTraversal(List<Integer> valuesToInsert, String expectedReverse) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (Integer integer : valuesToInsert) {
            list.insert(integer); // insert at tail
        }
        assertEquals(expectedReverse, list.reverseToString());
    }

    static Stream<Arguments> reverseTraversalDataProvider() {
        return Stream.of(
                Arguments.of(List.of(), ""),                                  // Empty list
                Arguments.of(List.of(10), "10"),                              // Single element
                Arguments.of(List.of(10, 20), "20 <-> 10"),                   // Two elements
                Arguments.of(List.of(10, 20, 30), "30 <-> 20 <-> 10")         // Multiple elements
        );
    }

    @ParameterizedTest(name = "Delete at index {1} in list {0} -> Expect: {2}")
    @MethodSource("deleteDataProvider")
    void testDeleteAtLocation(DoublyLinkedList<Integer> list, int index, Object expected) {
        if (expected instanceof String expectedList) {
            list.delete(index);
            assertEquals(expectedList, list.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> list.delete(index));
        }
    }

    static Stream<Arguments> deleteDataProvider() {
        return Stream.of(
                // Valid deletions
                Arguments.of(createList(10, 20, 30), 0, "20 <-> 30"),          // Delete head
                Arguments.of(createList(10, 20, 30), 1, "10 <-> 30"),          // Delete middle
                Arguments.of(createList(10, 20, 30), 2, "10 <-> 20"),          // Delete tail
                Arguments.of(createList(10), 0, ""),                           // Delete only element
                Arguments.of(createList(10, 20, 30, 40), 3, "10 <-> 20 <-> 30"), // Delete it last of 4
                Arguments.of(createList(10, 20, 30, 40), 2, "10 <-> 20 <-> 40"), // Delete it near the end
                Arguments.of(createList(10, 20, 30, 40), 1, "10 <-> 30 <-> 40"), // Delete it near start

                // Invalid deletions
                Arguments.of(createList(10, 20), -1, IllegalArgumentException.class),  // Negative index
                Arguments.of(createList(10, 20), 5, IllegalArgumentException.class),   // Index out of range
                Arguments.of(createList(), 0, EmptyStackException.class),        // Delete it from an empty list
                Arguments.of(createList(10), 1, IllegalArgumentException.class),      // Delete index too large
                Arguments.of(createList(10, 20), 100, IllegalArgumentException.class) // Wild out-of-bounds
        );
    }

    private static DoublyLinkedList<Integer> createList(Integer... values) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (Integer value : values) {
            list.insert(value);
        }
        return list;
    }

}