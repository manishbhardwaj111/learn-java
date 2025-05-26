package org.learn.datastructure.linkedlist;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SinglyLinkedList<T> {
    public Node<T> head, tail;
    public int size;

    public SinglyLinkedList(T data) {
        head = tail = new Node<>(data);
        size = 1;
    }

    public void rotate(int rotation) { // 10 -> 20 -> 30 -> 40
        if (rotation < 0 ) {
            throw new IllegalArgumentException("Invalid value for rotation count");
        } else if (size == 0 ) {
            throw new IllegalArgumentException("Empty SinglyLinkedList");
        } else if ((rotation %= size) != 0) {
            var prevNode = head;
            for (int index = 1; index < size - rotation; index++) {
                prevNode = prevNode.next;
            }
            tail.next = head;
            head = prevNode.next;
            tail = prevNode;
            prevNode.next = null;
        }
    }

    public void delete(int location) { // start from position 1
        if (head == null) {
            System.out.println("Empty SinglyLinkedList");
        } else if (location <= 0) {
            throw new IllegalArgumentException("Position must be >= 1");
        } else if (location > size) {
            throw new IllegalArgumentException("Position must be inbound with size");
        } else if (location == 1) {
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
        } else { // 10 -> 20 -> 30
            var currentNode = head;
            int index = 1;
            while (index < location - 1 && currentNode.next != null){
                index++;
                currentNode = currentNode.next;
            }
            if(currentNode.next != null && currentNode.next.next == null) {
                currentNode.next = null;
                tail = currentNode;
            }
            else if(currentNode.next != null) {
                currentNode.next = currentNode.next.next;
            }
            size--;
        }
    }

    public void insert(T data) {
        insert(data, size);
    }

    public void insert(T data, int location) {
        var newNode = new Node<>(data);
        if (size == 0) { // empty linkedList
            head = tail = newNode;
        } else if (location == 0) { // insert in beginning
            newNode.next = head;
            head = newNode;
        } else if (location >= size){ // insert at the end
            newNode.next = null;
            tail.next = newNode;
            tail = newNode;
        } else {
            int index = 0;
            var current = head;
            while (index < location - 1) {
                index++;
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public void printList() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != null) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
