package org.learn.datastructure.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphAdjacencyMatrix {
    List<GraphNode> nodeList;
    int[][] arr;

    public GraphAdjacencyMatrix(List<GraphNode> nodes) {
        this.nodeList = nodes;
        arr = new int[nodes.size()][nodes.size()];
    }

    public void addUndirectedEdge(int i, int j) {
        arr[i][j] = 1;
        arr[j][i] = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Header row with node names
        sb.append("    ");
        for (GraphNode node : nodeList) {
            sb.append(node.name).append("   ");
        }
        sb.append("\n");

        // Matrix rows with node names and edges
        for (int i = 0; i < arr.length; i++) {
            sb.append(nodeList.get(i).name).append(" | ");
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append("   ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(new GraphNode("A", 0));
        nodes.add(new GraphNode("B", 1));
        nodes.add(new GraphNode("C", 2));
        nodes.add(new GraphNode("D", 3));
        nodes.add(new GraphNode("E", 4));

        var graph = new GraphAdjacencyMatrix(nodes);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(3, 4);

        System.out.println(graph);

    }

    public ArrayList<GraphNode> getNeighbours(GraphNode node) {
        var neighbours = new ArrayList<GraphNode>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[node.index][i] == 1) {
                neighbours.add(nodeList.get(i));
            }
        }
        return neighbours;
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
            var neighbours = getNeighbours(currentNode);
            for (var neighbour: neighbours) {
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
            for (var child: getNeighbours(currentNode)) {
                if (!child.isVisited) {
                    child.isVisited = true;
                    child.parent = currentNode;
                    queue.add(child);
                }
            }
        }
    }
}
