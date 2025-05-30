package org.learn.datastructure.linkedlist;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.learn.datastructure.linkedlist.LinkedList.createList;

class QuestionsTest {

    @ParameterizedTest(name = "From list {0}, nth-to-last({1}) -> Expect: {2}")
    @MethodSource("returnNthToLastProvider")
    void testReturnNthToLast(LinkedList<Integer> list, int n, Object expected) {
        if (expected instanceof Integer expectedValue) {
            assertEquals(expectedValue, Questions.returnNthToLast(list, n));
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> Questions.returnNthToLast(list, n));
        }
    }

    static Stream<Arguments> returnNthToLastProvider() {
        return Stream.of(
                // Valid: list [10, 20, 30]
                Arguments.of(createList(10, 20, 30), 0, 30), // last element
                Arguments.of(createList(10, 20, 30), 1, 20), // second last
                Arguments.of(createList(10, 20, 30), 2, 10), // third last (first)

                // Invalid: index out of bounds
                Arguments.of(createList(10, 20, 30), -1, IllegalArgumentException.class),
                Arguments.of(createList(10, 20, 30), 3, IllegalArgumentException.class),

                // Valid: list [5]
                Arguments.of(createList(5), 0, 5),

                // Invalid: list [5], but n > 0
                Arguments.of(createList(5), 1, IllegalArgumentException.class),

                // Invalid: empty list
                Arguments.of(createList(), 0, IllegalArgumentException.class)
        );
    }

    @ParameterizedTest(name = "removeDuplicates({0}) -> Expect: {1}")
    @MethodSource("removeDuplicatesProvider")
    void testRemoveDuplicates(LinkedList<Integer> list, Object expected) {
        if (expected instanceof String expectedList) {
            Questions.removeDuplicates(list);
            assertEquals(expectedList, list.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> Questions.removeDuplicates(list));
        }
    }

    static Stream<Arguments> removeDuplicatesProvider() {
        return Stream.of(
                // ✅ No duplicates
                Arguments.of(createList(1, 2, 3), "1 -> 2 -> 3"),

                // ✅ With duplicates
                Arguments.of(createList(1, 2, 2, 3, 3, 3), "1 -> 2 -> 3"),
                Arguments.of(createList(1, 1, 1, 1), "1"),
                Arguments.of(createList(5, 5, 3, 5, 3, 1), "5 -> 3 -> 1"),

                // ✅ Single element
                Arguments.of(createList(42), "42"),

                // ✅ Empty list
                Arguments.of(createList(), ""),

                // ❌ Null list
                Arguments.of(null, NullPointerException.class)
        );
    }

    @ParameterizedTest(name = "Partition {0} around {1} -> Expect: {2}")
    @MethodSource("partitionDataProvider")
    void testPartitionAroundX(LinkedList<Integer> list, Integer x, Object expected) {
        if (expected instanceof String expectedList) {
            Questions.partitionAroundX(list, x);
            assertEquals(expectedList, list.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> Questions.partitionAroundX(list, x));
        }
    }

    static Stream<Arguments> partitionDataProvider() {
        return Stream.of(
                // ✅ General cases
                Arguments.of(createList(3, 5, 8, 5, 10, 2, 1), 5, "1 -> 2 -> 3 -> 5 -> 8 -> 5 -> 10"),
                Arguments.of(createList(1, 4, 3, 2, 5, 2), 3, "2 -> 2 -> 1 -> 4 -> 3 -> 5"),

                // ✅ All elements less than x
                Arguments.of(createList(1, 1, 1), 5, "1 -> 1 -> 1"),

                // ✅ All elements greater than or equal to x
                Arguments.of(createList(10, 20, 30), 5, "10 -> 20 -> 30"),

                // ✅ Mixed duplicates
                Arguments.of(createList(5, 1, 8, 0, 3, 5), 5, "3 -> 0 -> 1 -> 5 -> 8 -> 5"),

                // ✅ Single element
                Arguments.of(createList(7), 5, "7"),

                // ✅ Empty list
                Arguments.of(createList(), 5, ""),

                // ❌ Null list
                Arguments.of(null, 5, NullPointerException.class),

                // ❌ Null x
                Arguments.of(createList(1, 2, 3), null, NullPointerException.class)
        );
    }

    @ParameterizedTest(name = "sumLists({0}, {1}) = {2}")
    @MethodSource("sumListDataProvider")
    void testSumLists(LinkedList<Integer> list1, LinkedList<Integer> list2, Object expected) {
        if (expected instanceof String expectedList) {
            LinkedList<Integer> result = Questions.sumLists(list1, list2);
            assertEquals(expectedList, result.toString());
        } else if (expected instanceof Class<?>) {
            @SuppressWarnings("unchecked")
            Class<? extends Throwable> expectedException = (Class<? extends Throwable>) expected;
            assertThrows(expectedException, () -> Questions.sumLists(list1, list2));
        }
    }

    static Stream<Arguments> sumListDataProvider() {
        return Stream.of(
                // ✅ Valid test cases
                Arguments.of(createList(7, 1, 6), createList(5, 9, 2), "2 -> 1 -> 9"), // 617 + 295 = 912
                Arguments.of(createList(1, 2), createList(9, 9), "0 -> 2 -> 1"),       // 21 + 99 = 120
                Arguments.of(createList(0), createList(0), "0"),                      // 0 + 0 = 0
                Arguments.of(createList(9, 9, 9), createList(1), "0 -> 0 -> 0 -> 1"), // 999 + 1 = 1000
                Arguments.of(createList(1), createList(9, 9, 9), "0 -> 0 -> 0 -> 1"), // 1 + 999 = 1000

                // ❌ Invalid scenarios
                Arguments.of(null, createList(1, 2, 3), NullPointerException.class),
                Arguments.of(createList(4, 5), null, NullPointerException.class),
                Arguments.of(null, null, NullPointerException.class)
        );
    }


    @Disabled("This test is temporarily disabled")
    @ParameterizedTest(name = "Intersection of list1 and list2 should be node with data={2}")
    @MethodSource("intersectionDataProvider")
    void testIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2, Integer expectedData) {
        var result = Questions.intersection(list1, list2);
        if (expectedData == null) {
            assertFalse(result);
        } else {
            assertTrue(result);
        }
    }

    static Stream<Arguments> intersectionDataProvider() {
        return Stream.of(
                // ✅ Intersecting case
                createIntersectingLists(new Integer[]{3, 1, 5, 9}, new Integer[]{4, 6}, new Integer[]{7, 2, 1}), // intersect at 7

                // ❌ Non-intersecting case
                Arguments.of(createList(1, 2, 3), createList(4, 5, 6), null),

                // ✅ Identical reference list
                Arguments.of(createList(1, 2, 3), sameList(), 1),

                // ❌ One list is null
                Arguments.of(null, createList(1, 2, 3), null),
                Arguments.of(createList(1, 2), null, null),

                // ❌ Both lists are null
                Arguments.of(null, null, null)
        );
    }

    // Helper to create two lists that intersect at a shared node sequence
    private static Arguments createIntersectingLists(Integer[] vals1, Integer[] vals2, Integer[] shared) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> sharedList = new LinkedList<>();

        for (Integer val : vals1) list1.insert(val);
        for (Integer val : vals2) list2.insert(val);
        for (Integer val : shared) sharedList.insert(val);

        Node<Integer> sharedHead = sharedList.getHead();
        append(list1, sharedHead);
        append(list2, sharedHead);

        return Arguments.of(list1, list2, sharedHead.data);
    }

    private static void append(LinkedList<Integer> list, Node<Integer> node) {
        Node<Integer> current = list.getHead();
        if (current == null) {
            list.setHead(node);
            return;
        }
        while (current.next != null) current = current.next;
        current.next = node;
    }

    private static LinkedList<Integer> sameList() {
        return createList(5, 6, 7);
    }
}
