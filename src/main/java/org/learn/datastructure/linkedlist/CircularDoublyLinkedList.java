package org.learn.datastructure.linkedlist;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CircularDoublyLinkedList<T> {

    DoublyNode<T> head, tail;
    int size;

    public void delete(int location) {
        if (size == 0) {
            throw new IllegalStateException("Empty List");
        } else if (location < 0 || location >= size) {
            throw new IllegalArgumentException("Invalid location argument exception < 0 or > size");
        } else if (size == 1) {
            head = tail = null;
            size--;
        } else if (location == 0) {
            head = head.next;
            head.prev = tail;
            tail.next = head;
            size--;
        } else if (location == size - 1) {
            var prevNode = tail.prev;
            tail = prevNode;
            prevNode.next = head;
            head.prev = prevNode;
            size--;
        } else {
            var prevNode = head;
            for (int i = 0; i < location - 1 ; i++) {
                prevNode = prevNode.next;
            }
            var temp = prevNode.next.next;
            prevNode.next = temp;
            temp.prev = prevNode;
            size--;
        }
    }

    public void insert(T data, int location) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid data argument exception == null");
        }else if (location < 0 || location > size) {
            throw new IllegalArgumentException("Invalid location argument exception < 0 or > size");
        }
        else if (size == 0) {
            head = tail = new DoublyNode<>(data);
            head.next = head;
            size++;
        } else if (location == 0) {
           var newNode = new DoublyNode<>(data);
           var temp = head;
           head = newNode;
           newNode.next = temp;
           temp.prev = newNode;
           tail.next = head;
           head.prev = tail;
           size++;
        } else {
            var newNode = new DoublyNode<>(data);
            var prevNode = head;
            for (int i = 0; i < location - 1 ; i++) {
                prevNode = prevNode.next;
            }
            var temp = prevNode.next;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            if (temp == head) {
                tail = newNode;
                newNode.next = head;
                head.prev = tail;
            } else {
                newNode.next = temp;
                temp.prev = newNode;
            }
            size++;
        }
    }

    public void insert(T data) {
        insert(data, size);
    }

    private static class DoublyNode<T> {
        public DoublyNode<T> next, prev;
        public T data;

        public DoublyNode(T data){
            this.data = data;
        }

        @Override
        public String toString() {
            String prevData = (prev != null) ? prev.data.toString() : "null";
            String nextData = (next != null) ? next.data.toString() : "null";
            return prevData + " <- [" + data + "] -> " + nextData;
        }
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
        if (head == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        var current = head;
        do {
            assert current != null;
            sb.append(current.data);
            current = current.next;
            if (current != head) {
                sb.append(" <-> ");
            }
        } while (current != head);
        sb.append(" (circular)");
        return sb.toString();
    }
}
