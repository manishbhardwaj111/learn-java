package org.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraph {
    public List<WeightedNode> nodeList;

    public WeightedGraph(List<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }

    public void dijkstra(WeightedNode source) {
        source.distance = 0;
        var queue = new PriorityQueue<WeightedNode>();
        queue.addAll(nodeList);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            for (var neighbour: currentNode.neighbours) {
                if (queue.contains(neighbour)) {
                    if (neighbour.distance > currentNode.distance + currentNode.weightMap.get(neighbour)) {
                        neighbour.distance = currentNode.distance + currentNode.weightMap.get(neighbour);
                        neighbour.parent = currentNode;
                        queue.remove(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
        }
        for (var nodeToCheck: nodeList) {
            System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.distance + ", Path: ");
            pathPrint(nodeToCheck);
            System.out.println();
        }
    }

    public void dijkstra2(WeightedNode source) {
        source.distance = 0;
        PriorityQueue<WeightedNode> queue = new PriorityQueue<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            WeightedNode currentNode = queue.poll();
            currentNode.isVisited = true;
            for (WeightedNode neighbor : currentNode.neighbours) {
                if (!neighbor.isVisited) {
                    int edgeWeight = currentNode.weightMap.get(neighbor);
                    int newDistance = currentNode.distance + edgeWeight;
                    if (newDistance < neighbor.distance) {
                        neighbor.distance = newDistance;
                        neighbor.parent = currentNode;
                        queue.add(neighbor);
                    }
                }
            }
        }

        // Print result (optional)
        for (WeightedNode node : nodeList) {
            System.out.println("Distance from " + source + " to " + node + " = " + node.distance);
        }
    }

    public void bellmanFordAlgorithm(WeightedNode source) {
        source.distance = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            for (var currentNode : nodeList) {
                for (var neighbour: currentNode.neighbours) {
                    var newDistance = currentNode.distance + currentNode.weightMap.get(neighbour);
                    if (neighbour.distance > newDistance) {
                        neighbour.distance = newDistance;
                    }
                }
            }
        }
        System.out.println("Checking for Negative Cycle...");
        for (var currentNode : nodeList) {
            for (var neighbour: currentNode.neighbours) {
                var newDistance = currentNode.distance + currentNode.weightMap.get(neighbour);
                if (neighbour.distance > newDistance) {
                    System.out.println("Negative Cycle found...");
                    System.out.println("From Node : " + currentNode + "->" + neighbour + ", " +
                            "Old distance : " + neighbour.distance + ", New distance : " + newDistance);
                    return;
                }
            }
        }
        System.out.println("Printing SSSPP for all Nodes");
        for (var nodeToCheck: nodeList) {
            System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.distance + ", Path: ");
            pathPrint(nodeToCheck);
            System.out.println();
        }
    }

    public void addWeightedEdge(int i, int j, int distance) {
        var sourceNode = nodeList.get(i);
        var destinationNode = nodeList.get(j);
        sourceNode.neighbours.add(destinationNode);
        sourceNode.weightMap.put(destinationNode, distance);
    }

    public void pathPrint(WeightedNode node) {
        if (node.parent != null ) {
            pathPrint(node.parent);
        }
        System.out.print(node.name + " ");
    }

    public void floydWarshall() {
        int[][] array = new int[nodeList.size()][nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (i == j) {
                    array[i][j] = 0;
                } else {
                    var first = nodeList.get(i);
                    var second = nodeList.get(j);
                    array[i][j] = first.weightMap.getOrDefault(second, Integer.MAX_VALUE/10);
                }
            }
        }
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                for (int k = 0; k < nodeList.size(); k++) {
                    if (array[j][k] > array[j][i] + array[i][k]) {
                        array[j][k] = array[j][i] + array[i][k];
                    }
                }
            }
        }
        System.out.print("    ");
        nodeList.forEach(node -> System.out.print(node.name + "  "));
        System.out.println();
        for (int i = 0; i < nodeList.size(); i++) {
            System.out.print(nodeList.get(i).name + " : ");
            for (int j = 0; j < nodeList.size(); j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void floydMarshall_Example() {
        ArrayList<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A",0));
        nodeList.add(new WeightedNode("B",1));
        nodeList.add(new WeightedNode("C",2));
        nodeList.add(new WeightedNode("D",3));
        WeightedGraph newGraph = new WeightedGraph(nodeList);
        newGraph.addWeightedEdge(0,3,1);
        newGraph.addWeightedEdge(0,1,8);
        newGraph.addWeightedEdge(1,2,1);
        newGraph.addWeightedEdge(2,0,4);
        newGraph.addWeightedEdge(3,1,2);
        newGraph.addWeightedEdge(3,2,9);
        System.out.println("Printing Floyd Warshall algorithm");
        newGraph.floydWarshall();
    }

    public static void main(String[] args) {
        floydMarshall_Example();

        ArrayList<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A", 0));
        nodeList.add(new WeightedNode("B", 1));
        nodeList.add(new WeightedNode("C", 2));
        nodeList.add(new WeightedNode("D", 3));
        nodeList.add(new WeightedNode("E", 4));
        nodeList.add(new WeightedNode("F", 5));
        nodeList.add(new WeightedNode("G", 6));

        WeightedGraph newGraph = new WeightedGraph(nodeList);
        newGraph.addWeightedEdge(0, 1, 2);
        newGraph.addWeightedEdge(0, 2, 5);
        newGraph.addWeightedEdge(1, 2, 6);
        newGraph.addWeightedEdge(1, 3, 1);
        newGraph.addWeightedEdge(1, 4, 3);
        newGraph.addWeightedEdge(2, 5, 8);
        newGraph.addWeightedEdge(3, 4, 4);
        newGraph.addWeightedEdge(4, 6, 9);
        newGraph.addWeightedEdge(5, 6, 7);

//        System.out.println("Printing Dijkstra from Source : A");
//        newGraph.dijkstra(nodeList.getFirst());
//        System.out.println("=======================================");
//        newGraph.dijkstra(nodeList.getFirst());
//   q     newGraph.bellmanFordAlgorithm(nodeList.getFirst());

    }
}
