package org.learn.datastructure.linkedlist;

import lombok.NoArgsConstructor;

import java.util.EmptyStackException;

@NoArgsConstructor
public class CircularSinglyLinkedList<T> {
    public Node<T> head, tail;
    public int size;

    public CircularSinglyLinkedList(T data) {
        head = tail = new Node<>(data);
        head.next = head;
        size = 1;
    }

    public void delete(int location) {
        if (size == 0) {
            throw new EmptyStackException();
        } else if (location < 0 || location > size){
            throw new IllegalArgumentException("Invalid Location for delete ops");
        } else if (size == 1) {
            head = tail = null;
            size--;
        } else if (location == 1) {
            head = head.next;
            tail.next = head;
            size--;
        } else {
            var prevNode = head;
            for (int i = 1; i < location - 1; i++) {
                prevNode = prevNode.next;
            }
            if (size == location) {
                tail = prevNode;
                tail.next = head;
            } else {
                prevNode.next = prevNode.next.next;
            }
            size--;
        }
    }

    public void insert(T data, int location) {
        if (location < 0) {
            throw new IllegalArgumentException("Invalid Location");
        }
        else if (size == 0) {
            head = tail = new Node<>(data);
            head.next = head;
            size++;
        }else if (location == 0) {
            var temp = head;
            head = new Node<>(data);
            head.next = temp;
            tail.next = head;
            size++;
        } else if (location >= size) {
            tail.next = new Node<>(data);
            tail = tail.next;
            tail.next = head;
            size++;
        } else {
            var prevNode = head;
            for (int i=1; i < location; i++) {
                prevNode = prevNode.next;
            }
            var temp = prevNode.next;
            prevNode.next = new Node<>(data);
            prevNode.next.next = temp;
            size++;
        }

    }

    public static void main(String[] args) {
        var circularSinglyLinkedList = new CircularSinglyLinkedList<>(10);
        circularSinglyLinkedList.printList();
    }

    public void printList() {
        System.out.println(this);
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
                sb.append(" -> ");
            }
        } while (current != head);
        sb.append(" (circular)");
        return sb.toString();
    }
}
