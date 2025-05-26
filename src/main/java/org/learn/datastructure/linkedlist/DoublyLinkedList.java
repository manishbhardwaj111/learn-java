package org.learn.datastructure.linkedlist;

import lombok.NoArgsConstructor;

import java.util.EmptyStackException;

@NoArgsConstructor
public class DoublyLinkedList<T> {
    DoublyNode<T> head, tail;
    int size;

    public DoublyLinkedList(T data) {
        head = tail = new DoublyNode<>(data);
        size++;
    }

    public void delete(int location) {
        if (size == 0) {
            throw new EmptyStackException();
        } else if (location < 0 || location >= size) {
            throw new IllegalArgumentException("Invalid location argument exception < 0 or > size");
        } else if (size == 1) {
            head = tail = null;
            size--;
        } else if (location == 0) {
            head = head.next;
            head.prev = null;
            size--;
        } else if (location == (size - 1)) {
            var prevNode = tail.prev;
            prevNode.next = null;
            tail = prevNode;
            size--;
        } else {
            var prevNode = head;
            for (int i = 0; i < location - 1; i++) {
                prevNode = prevNode.next;
            }
            var temp = prevNode.next.next;
            prevNode.next = temp;
            temp.prev = prevNode;
            size--;
        }
    }

    public void insert(T data, int location) {
        var newNode = new DoublyNode<>(data);
        if (size == 0) { // empty linkedList
            head = tail = new DoublyNode<>(data);
            size++;
        } else if (location < 0 || location > size) { // insert in beginning
            throw new IllegalArgumentException("Invalid location argument exception <= 0");
        } else if (location == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
        } else if (location == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        } else {
            var prevNode = head;
            for (int i = 0; i < location - 1; i++) {
                prevNode = prevNode.next;
            }
            var temp = prevNode.next;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = temp;
            temp.prev = newNode;
            size++;
        }
    }

    public void insert(T data) {
        insert(data, size);
    }

    public String reverseToString() {
        StringBuilder sb = new StringBuilder();
        var current = tail;
        while (current != null) {
            sb.append(current.data);
            current = current.prev;
            if (current != null) {
                sb.append(" <-> ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != null) {
                sb.append(" <-> ");
            }
        }
        return sb.toString();
    }

    private static class DoublyNode<T> {
        public DoublyNode<T> next, prev;
        public T data;

    public DoublyNode(T data){
            this.data = data;
        }
    }

}
