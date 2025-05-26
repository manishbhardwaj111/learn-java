package org.learn.datastructure.graph;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedGraphTest {

    @BeforeEach
    void setup(){

    }

    static Stream<TestCase> graphProvider() {
        return Stream.of(
                simpleGraph(),
                triangleGraph(),
                disconnectedGraph()
        );
    }

    static TestCase simpleGraph() {
        WeightedNode A = new WeightedNode("A", 0);
        WeightedNode B = new WeightedNode("B", 1);

        A.neighbours.add(B);
        A.weightMap.put(B, 5);

        List<WeightedNode> nodes = Arrays.asList(A, B);
        return new TestCase(A, nodes, Map.of("A", 0, "B", 5));
    }

    static TestCase triangleGraph() {
        WeightedNode A = new WeightedNode("A", 0);
        WeightedNode B = new WeightedNode("B", 1);
        WeightedNode C = new WeightedNode("C", 2);

        A.neighbours.add(B); A.weightMap.put(B, 2);
        B.neighbours.add(C); B.weightMap.put(C, 3);
        A.neighbours.add(C); A.weightMap.put(C, 10);

        List<WeightedNode> nodes = Arrays.asList(A, B, C);
        return new TestCase(A, nodes, Map.of("A", 0, "B", 2, "C", 5));
    }

    static TestCase disconnectedGraph() {
        WeightedNode A = new WeightedNode("A", 0);
        WeightedNode B = new WeightedNode("B", 1);

        // No edges
        List<WeightedNode> nodes = Arrays.asList(A, B);
        return new TestCase(A, nodes, Map.of("A", 0, "B", Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @MethodSource("graphProvider")
    void testDijkstra(TestCase testCase) {
        // Reset distances and visited flags
        for (WeightedNode node : testCase.allNodes) {
            node.distance = Integer.MAX_VALUE;
            node.isVisited = false;
            node.parent = null;
        }

        var weightedGraph = new WeightedGraph(testCase.allNodes);
        weightedGraph.dijkstra(testCase.source);

        for (WeightedNode node : testCase.allNodes) {
            int expected = testCase.expectedDistances.get(node.name);
            assertEquals(expected, node.distance, "Distance to " + node.name + " is incorrect.");
        }
    }

    static class TestCase {
        WeightedNode source;
        List<WeightedNode> allNodes;
        Map<String, Integer> expectedDistances;

        TestCase(WeightedNode source, List<WeightedNode> allNodes, Map<String, Integer> expectedDistances) {
            this.source = source;
            this.allNodes = allNodes;
            this.expectedDistances = expectedDistances;
        }
    }

}