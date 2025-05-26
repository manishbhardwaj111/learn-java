package org.learn.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Number & Comparable<T>> {

    public BinaryNode<T> root;

    public void insert(T value) {
        root = insert(root, value);
    }

    private BinaryNode<T> insert(BinaryNode<T> currentNode, T value) {
        if (currentNode == null) {
            System.out.println("Successfully inserted node "+ value);
            return new BinaryNode<>(value);
        } else {
            if (value.compareTo(currentNode.value) <= 0) {
                currentNode.left = insert(currentNode.left, value);
            } else {
                currentNode.right = insert(currentNode.right, value);
            }
        }
        return currentNode;
    }

    public void preOrder(BinaryNode<T> node) {
        if (node != null) {
            System.out.println(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(BinaryNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.value + " ");
        }
    }

    public void inOrder(BinaryNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.value + " ");
            inOrder(node.right);
        }
    }

    public void levelOrder() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> currentNode = queue.remove();
            System.out.println(currentNode.value + " ");
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public boolean search(T value) {
        return search(root, value) != null;
    }

    public BinaryNode<T> search(BinaryNode<T> node, T value) {
        if (node == null) {
            return null;
        } else if (node.value.compareTo(value) == 0) {
            return node;
        } else if (node.value.compareTo(value) > 0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(70);
        tree.insert(50);
        tree.insert(90);
        tree.printTree();
        System.out.println(tree.search( 90));
    }

    public void printTree() {
        System.out.println("========== Binary Tree =========================");
        printTree(root, 0);
    }

    private void printTree(BinaryNode<T> node, int level) {
        if (node == null) {
            return;
        }
        printTree(node.right, level + 1);
        System.out.print("    ".repeat(level));
        System.out.println(node.value);
        printTree(node.left, level + 1);
    }

}
