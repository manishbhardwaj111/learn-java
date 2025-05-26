package org.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {
    public List<WeightedNode> nodeList;

    public PrimsAlgorithm(List<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }

    public void addWeightedUndirectedEdge(int i, int j, int distance) {
        var first = nodeList.get(i);
        var second = nodeList.get(j);
        first.neighbours.add(second);
        second.neighbours.add(first);
        first.weightMap.put(second, distance);
        second.weightMap.put(first, distance);
    }

    public void prims(WeightedNode sourceNode) {
        for (var node: nodeList) {
            node.distance = Integer.MAX_VALUE;
        }
        sourceNode.distance = 0;
        var priorityQueue = new PriorityQueue<WeightedNode>();
        priorityQueue.add(sourceNode);
        while (!priorityQueue.isEmpty()) {
            var currentNode = priorityQueue.remove();
            currentNode.isVisited = true;
            for (WeightedNode neighbor : currentNode.neighbours) {
                if (!neighbor.isVisited) {
                    int edgeWeight = currentNode.weightMap.get(neighbor);
                    if (neighbor.distance > edgeWeight) {
                        neighbor.distance = edgeWeight;
                        neighbor.parent = currentNode;
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
        int cost = 0;
        for (var nodeToCheck: nodeList) {
            System.out.print(nodeToCheck.name + "  ");
            cost +=nodeToCheck.distance;
        }
        System.out.println();
        System.out.println("Total Cost = " + cost);
    }

    public static void main(String[] args) {
        List<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A", 0));
        nodeList.add(new WeightedNode("B", 1));
        nodeList.add(new WeightedNode("C", 2));
        nodeList.add(new WeightedNode("D", 3));
        nodeList.add(new WeightedNode("E", 4));

        PrimsAlgorithm graph = new PrimsAlgorithm(nodeList);
        graph.addWeightedUndirectedEdge(0, 1, 5);
        graph.addWeightedUndirectedEdge(0, 2, 13);
        graph.addWeightedUndirectedEdge(0, 4, 15);
        graph.addWeightedUndirectedEdge(1, 2, 10);
        graph.addWeightedUndirectedEdge(1, 3, 8);
        graph.addWeightedUndirectedEdge(2, 3, 6);
        graph.addWeightedUndirectedEdge(2, 4, 20);

        graph.prims(nodeList.get(4));
    }
}
