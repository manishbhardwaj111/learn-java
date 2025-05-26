package org.learn.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLinkedList<T> {

    BinaryNode<T> root;

    public BinaryTreeLinkedList(){
        this.root = null;
    }

    public void preOrder(BinaryNode<T> root) {
        if (root != null) {
            System.out.print(root.value + " -> ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(BinaryNode<T> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + " -> ");
            inOrder(root.right);
        }
    }

    public void postOrder(BinaryNode<T> root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + " -> ");
        }
    }

    public void levelOrder(BinaryNode<T> root) {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            System.out.print(currentNode.value + " -> ");
            if (currentNode.left != null)
                queue.add(currentNode.left);
            if (currentNode.right != null)
                queue.add(currentNode.right);
        }
    }

    public void search(T value) {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            if (currentNode.value.equals(value)) {
                System.out.println(value + " => Found");
                return;
            }
            if (currentNode.left != null)
                queue.add(currentNode.left);
            if (currentNode.right != null)
                queue.add(currentNode.right);
        }
        System.out.println(value + " => Not Found");
    }

    public void insert(T value) {
        if (root == null) {
            root = new BinaryNode<>(value);
            return;
        }
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            var currentNode = queue.remove();
            if (currentNode.left != null)
                queue.add(currentNode.left);
            else {
                currentNode.left = new BinaryNode<>(value);
                return;
            }
            if (currentNode.right != null)
                queue.add(currentNode.right);
            else {
                currentNode.right = new BinaryNode<>(value);
                return;
            }
        }
    }

    public BinaryNode<T> findDeepestNode() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<T> presentNode = null;
        while (!queue.isEmpty()) {
            presentNode = queue.remove();
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
        return presentNode;
    }

    public void deleteDeepestNode() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<T> presentNode = null, previousNode = null;
        while (!queue.isEmpty()) {
            previousNode = presentNode;
            presentNode = queue.remove();
            if (presentNode.left == null) {
                assert previousNode != null;
                previousNode.right = null;
                return;
            }
            if (presentNode.right == null) {
                presentNode.left = null;
                return;
            }
            queue.add(presentNode.left);
            queue.add(presentNode.right);
        }
    }

    public void deleteNode(T value) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<T> presentNode;
        while (!queue.isEmpty()) {
            presentNode = queue.remove();
            if (presentNode.value.equals(value)) {
                presentNode.value = findDeepestNode().value;
                deleteDeepestNode();
                return;
            } else {
                if (presentNode.left != null) queue.add(presentNode.left);
                if (presentNode.right != null) queue.add(presentNode.right);
            }
        }
    }


    public static void main(String[] args) {
        var binaryTree = creteSampleBinaryTree();
        binaryTree.printTree();
        binaryTree.insert("N10");
        binaryTree.printTree();
    }

     //                   N1
     //                 /    \
     //              N2       N3
     //            /   \     /  \
     //          N4     N5  N6  N7
     //        /   \
     //       N8   N9

    public static BinaryTreeLinkedList<String> creteSampleBinaryTree() {

        var binaryTree = new BinaryTreeLinkedList<String>();
        BinaryNode<String> N1 = new BinaryNode<>("N1");
        BinaryNode<String> N2 = new BinaryNode<>("N2");
        BinaryNode<String> N3 = new BinaryNode<>("N3");
        BinaryNode<String> N4 = new BinaryNode<>("N4");
        BinaryNode<String> N5 = new BinaryNode<>("N5");
        BinaryNode<String> N6 = new BinaryNode<>("N6");
        BinaryNode<String> N7 = new BinaryNode<>("N7");
        BinaryNode<String> N8 = new BinaryNode<>("N8");
        BinaryNode<String> N9 = new BinaryNode<>("N9");

        N1.left = N2;N1.right = N3;
        N2.left = N4;N2.right = N5;
        N3.left = N6;N3.right = N7;
        N4.left = N8;N4.right = N9;
        binaryTree.root = N1;
        return binaryTree;
    }

    public void printTree() {
        System.out.println("========== Binary Tree =========================");
        printTree(this.root, 0);
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
