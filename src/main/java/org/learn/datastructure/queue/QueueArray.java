package org.learn.datastructure.queue;

public class QueueArray<T> {
    private final Object[] array;
    private int front;
    private int rear;

    public QueueArray(int capacity){
        array = new Object[capacity];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == array.length - 1;
    }

    public boolean isEmpty() {
        return front == -1 || front == array.length;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        } else if(isEmpty()) {
            front = 0;
            rear++;
            array[rear] = item;
        } else {
            rear++;
            array[rear] = item;
        }
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            T item = (T) array[front];
            front++;
            if (front > rear) {
                front = rear = -1;
            }
            return item;
        }
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            return (T) array[front];
        }
    }
}
