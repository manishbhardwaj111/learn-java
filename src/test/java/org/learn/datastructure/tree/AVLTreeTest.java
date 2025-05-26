package org.learn.datastructure.tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AVLTreeTest {

    static class TestCase {
        List<Integer> inputValues;
        String expectedRoot;

        TestCase(List<Integer> inputValues, String expectedRoot) {
            this.inputValues = inputValues;
            this.expectedRoot = expectedRoot;
        }

        @Override
        public String toString() {
            return "TestCase{" +
                    "inputValues=" + inputValues +
                    ", expectedRoot='" + expectedRoot + '\'' +
                    '}';
        }
    }

    static Stream<TestCase> provideInsertionScenarios() {
        return Stream.of(
                // Basic Rotations
                new TestCase(List.of(30, 20, 10), "20"), // LL
                new TestCase(List.of(10, 20, 30), "20"), // RR
                new TestCase(List.of(30, 10, 20), "20"), // LR
                new TestCase(List.of(10, 30, 20), "20"), // RL

                // Complex 1: Cascade balancing with multiple rotations
                // Insertions: 10, 20, 30, 40, 50, 25
                // Rotations: RR on 10-20-30, then RL on 40-50-25
                // Final root after complete balance = 30
                new TestCase(List.of(10, 20, 30, 40, 50, 25), "30"),

                // Complex 2: Full balanced tree, then rebalancing after leaf insert
                new TestCase(List.of(40, 20, 60, 10, 30, 50, 70, 5), "40"),

                // Complex 3: Inserting ascending values (1-10) causing multiple RR rotations
                new TestCase(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), "4"),

                // Complex 4: Inserting descending values (10-1) causing multiple LL rotations
                new TestCase(List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), "7"),

                // Complex 5: Zigzag pattern insertion (50, 30, 70, 20, 40, 35, 60)
                // Causes LR at 30 and RL at 70
                // TODO check
                new TestCase(List.of(50, 30, 70, 20, 40, 35, 60), "50"),

                // Complex 6: Insert into a deep balanced tree, rebalancing propagates up
                // Causes rebalance from a grandchild level all the way to the root
                new TestCase(List.of(100, 50, 150, 25, 75, 125, 175, 10), "100")
        );
    }

    @ParameterizedTest(name = "Insertion Test {index}: Input {0}")
    @MethodSource("provideInsertionScenarios")
    @DisplayName("Test AVL Insertions for All Rotations")
    void testInsertions(TestCase testCase) {
        AVLTree<Integer> avl = new AVLTree<>();

        for (Integer val : testCase.inputValues) {
            avl.insert(val);
        }
        avl.printTree();
        assertEquals(testCase.expectedRoot, avl.root.toString(),
                "Root should be " + testCase.expectedRoot + " after balancing");
    }
}