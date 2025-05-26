package org.learn.datastructure.linkedlist;

public class Node<T> {
    @Override
    public String toString() {
        if (next != null) {
            return data + " -> " + next.data;
        } else {
            return data + " -> null";
        }
    }

    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

}
