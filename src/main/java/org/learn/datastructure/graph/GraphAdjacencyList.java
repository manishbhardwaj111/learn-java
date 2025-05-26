package org.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphAdjacencyList {
    List<GraphNode> nodeList;

    public GraphAdjacencyList(List<GraphNode> nodes) {
        this.nodeList = nodes;
    }

    public void addUndirectedEdge(int i, int j) {
        GraphNode first = nodeList.get(i);
        GraphNode second = nodeList.get(j);
        first.neighbours.add(second);
        second.neighbours.add(first);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (GraphNode graphNode : nodeList) {
            s.append(graphNode.name).append(": ");
            for (int j = 0; j < graphNode.neighbours.size(); j++) {
                if (j == graphNode.neighbours.size() - 1) {
                    s.append((graphNode.neighbours.get(j).name));
                } else {
                    s.append(graphNode.neighbours.get(j).name).append(" -> ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(new GraphNode("A", 0));
        nodes.add(new GraphNode("B", 1));
        nodes.add(new GraphNode("C", 2));
        nodes.add(new GraphNode("D", 3));
        nodes.add(new GraphNode("E", 4));

        var graph = new GraphAdjacencyList(nodes);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(3, 4);

        System.out.println(graph);

    }

    public void bfs() {
        for (var node: nodeList) {
            if (!node.isVisited) {
                bfs(node);
            }
        }
    }

    private void bfs(GraphNode node) {
        var queue = new LinkedList<GraphNode>();
        queue.add(node);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            currentNode.isVisited = true;
            System.out.println(currentNode.name + " ");
            for (var neighbour: currentNode.neighbours) {
                if (!neighbour.isVisited) {
                    neighbour.isVisited = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    public void pathPrint(GraphNode node) {
        if (node.parent != null ) {
            pathPrint(node.parent);
        }
        System.out.print(node.name + " -> ");
    }

    public void BFSForSSSPP(GraphNode node) {
        var queue = new LinkedList<GraphNode>();
        queue.add(node);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            currentNode.isVisited = true;
            System.out.print("Printing path for Node = " + currentNode.name + ": ");
            pathPrint(currentNode);
            System.out.println();
            for (var child: currentNode.neighbours) {
                if (!child.isVisited) {
                    child.isVisited = true;
                    child.parent = currentNode;
                    queue.add(child);
                }
            }
        }
    }
}
