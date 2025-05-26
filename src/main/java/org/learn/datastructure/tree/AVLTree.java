package org.learn.datastructure.tree;

public class AVLTree<T extends Comparable<T>> {
    public BinaryNode<T> root;

    public int getHeight(BinaryNode<T> node) {
        return node == null ? 0 : node.height;
    }

    private BinaryNode<T> rotateRight(BinaryNode<T> disbalanceNode) {
        var newRoot = disbalanceNode.left;
        disbalanceNode.left = disbalanceNode.left.right;
        newRoot.right = disbalanceNode;
        disbalanceNode.height = 1 + Math.max(getHeight(disbalanceNode.left), getHeight(disbalanceNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    private BinaryNode<T> rotationLeft(BinaryNode<T> disbalanceNode) {
        var newRoot = disbalanceNode.right;
        disbalanceNode.right = disbalanceNode.right.left;
        newRoot.left = disbalanceNode;
        disbalanceNode.height = 1 + Math.max(getHeight(disbalanceNode.left), getHeight(disbalanceNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    public int getBalance(BinaryNode<T> node) {
        return node == null ? 0 : (getHeight(node.left) - getHeight(node.right));
    }

    private  BinaryNode<T> insert(BinaryNode<T> node, T nodeValue) {
        if (node == null) {
            return new BinaryNode<>(nodeValue);
        } else if (nodeValue.compareTo(node.value) < 0) {
            node.left = insert(node.left, nodeValue);
        } else {
            node.right = insert(node.right, nodeValue);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && nodeValue.compareTo(node.left.value) < 0) {
            return rotateRight(node);
        }

        if (balance > 1 && nodeValue.compareTo(node.left.value) > 0) {
            node.left = rotationLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && nodeValue.compareTo(node.right.value) > 0) {
            return rotationLeft(node);
        }

        if (balance < -1 && nodeValue.compareTo(node.right.value) < 0) {
            node.right = rotateRight(node.right);
            return rotationLeft(node);
        }

        return node;
    }

    public void insert(T nodeValue) {
        root = insert(root, nodeValue);
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(BinaryNode<T> node, String prefix, boolean isTail) {
        if (node == null) return;

        if (node.right != null) {
            printTree(node.right, prefix + (isTail ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.value);

        if (node.left != null) {
            printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
