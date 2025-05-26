package org.learn.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedNode implements Comparable<WeightedNode>{
    public String name;
    public List<WeightedNode> neighbours;
    public Map<WeightedNode, Integer> weightMap;
    public boolean isVisited;
    public WeightedNode parent;
    public int distance;
    public int index;

    public WeightedNode(String name, int index) {
        this.name = name;
        this.index = index;
        neighbours = new ArrayList<>();
        weightMap = new HashMap<>();
        isVisited = false;
        distance = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(WeightedNode obj) {
        return this.distance - obj.distance;
    }
}
