package org.learn.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblem {
    private static final int MAX_COUNT = 10;
    private final Queue<String> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition added = lock.newCondition();
    private final Condition removed = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == MAX_COUNT) {
                removed.await();
            }
            addData();
            added.signal();
        } finally {
            lock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                added.await();
            }
            String data = getData();
            removed.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    private void addData() {
        queue.add("Data" + (queue.size() + 1));
        System.out.println("Produced: Data" + (queue.size()-1));
    }

    private String getData() {
        String data = queue.poll();
        System.out.println("Consumed: " + data);
        return data;
    }

    public static void main(String[] args) {
        ProducerConsumerProblem problem = new ProducerConsumerProblem();

        // Producer thread
        Thread producerThread = new Thread(() -> {
            try {
                while (true) {
                    problem.produce();
                    Thread.sleep(500); // simulate time to produce data
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    problem.consume();
                    Thread.sleep(1000); // simulate time to consume data
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
