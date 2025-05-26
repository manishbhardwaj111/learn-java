package org.learn.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

public class NormalTree<T> {

    private final TreeNode<T> rootNode;

    private static class TreeNode<T> {
        public T value;
        public List<TreeNode<T>> children;

        public TreeNode(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode<T> child) {
            children.add(child);
        }
    }

    public NormalTree(T value) {
        rootNode = new TreeNode<>(value);
    }

    public void addChild(NormalTree<T> child) {
        rootNode.addChild(child.rootNode);
    }

    public String print(int level) {
        return printTree(rootNode, level);
    }

    private String printTree(TreeNode<T> node, int level) {
        StringBuilder result = new StringBuilder("  ".repeat(level) + node.value + "\n");
        for (TreeNode<T> child : node.children) {
            result.append(printTree(child, level + 1));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        NormalTree<String> drinks = new NormalTree<>("Drinks");
        NormalTree<String> hot = new NormalTree<>("Hot");
        NormalTree<String> cold = new NormalTree<>("Cold");
        NormalTree<String> tea = new NormalTree<>("Tea");
        NormalTree<String> coffee = new NormalTree<>("Coffee");
        NormalTree<String> wine = new NormalTree<>("Wine");
        NormalTree<String> beer = new NormalTree<>("Beer");

        drinks.addChild(hot);
        drinks.addChild(cold);
        hot.addChild(tea);
        hot.addChild(coffee);
        cold.addChild(wine);
        cold.addChild(beer);

        System.out.println(drinks.print(0));
    }
}
