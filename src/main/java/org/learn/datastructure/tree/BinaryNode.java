package org.learn.datastructure.tree;

public class BinaryNode<T> {
    public T value;
    public BinaryNode<T> left, right;
    public int height;

    public BinaryNode(T value) {
        this.value = value;
        this.height = 1;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}