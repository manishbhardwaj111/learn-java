package org.learn.datastructure.tree;

public class BinaryTreeArray<T> {
    Object[] arr;
    int lastIndexUsed;

    public BinaryTreeArray(int capacity) {
        arr = new Object[capacity];
        lastIndexUsed = 0;
    }
}
