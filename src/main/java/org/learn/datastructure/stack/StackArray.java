package org.learn.datastructure.stack;

public class StackArray<T> {
    private final Object[] array;
    private int top = -1;

    public StackArray(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public void push(T data){
        if(isFull()) {
            throw new IllegalStateException("Stack is full");
        } else {
            array[top+1] = data;
            top++;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        } else {
            var data = (T) array[top];
            top--;
            return data;
        }
    }

    @SuppressWarnings("unchecked")
    public T peek(){
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        } else {
            return (T) array[top];
        }
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top + 1 == array.length;
    }
}
