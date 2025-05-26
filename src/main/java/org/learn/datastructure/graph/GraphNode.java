package org.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    public String name;
    public int index;
    public boolean isVisited;
    public List<GraphNode> neighbours;
    public GraphNode parent;

    public GraphNode(String name, int index) {
        this.name = name;
        this.index = index;
        neighbours = new ArrayList<>();
    }
}
