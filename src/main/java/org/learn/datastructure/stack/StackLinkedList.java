package org.learn.datastructure.stack;

import org.learn.datastructure.linkedlist.LinkedList;

public class StackLinkedList<T> {
    private final LinkedList<T> list;

    public StackLinkedList(){
        list = new LinkedList<>();
    }

    public boolean isEmpty() {
        return list.getSize() == 0;
    }

    public void push(T data) {
        System.out.println(list.toString());
        list.insert(data, 0);
    }

    public T pop() {
        System.out.println(list.toString());
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        } else {
            var data = list.getHead().data;
            list.delete(0);
            return data;
        }
    }

    public T peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        } else {
            return list.getHead().data;
        }
    }
}
