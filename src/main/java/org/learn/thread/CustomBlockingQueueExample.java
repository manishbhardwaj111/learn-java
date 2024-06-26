package org.learn.thread;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueueExample {
    public static void main(String[] args) {
        CustomBlockingQueue<Integer> customBlockingQueue = new CustomBlockingQueue<>(5);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    customBlockingQueue.put(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    customBlockingQueue.get();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}

class CustomBlockingQueue<T> {
    private final Object lock;
    private final int MAX_SIZE;
    private int size;
    private final Queue<T> queue;

    public CustomBlockingQueue(int maxSize) {
        this.MAX_SIZE = maxSize;
        this.size = 0;
        this.queue = new LinkedList<>();
        this.lock = new Object();
    }

    public void get() throws InterruptedException {
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }
            size--;
            T item = queue.poll();
            System.out.println("Consumed: " + item);
            lock.notifyAll();
        }
    }

    public void put(T item) throws InterruptedException {
        synchronized (lock) {
            while (size == MAX_SIZE) {
                lock.wait();
            }
            queue.add(item);
            size++;
            System.out.println("Produced: " + item);
            lock.notifyAll();
        }
    }
}
