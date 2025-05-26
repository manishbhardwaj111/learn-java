package org.learn.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
    
    private int sharedData = 1;

    public void readData() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Reading data: " + sharedData);
        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int data) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Writing data: " + data);
            sharedData = data;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();

        Runnable readTask = () -> {
            for (int i = 0; i < 5; i++) {
                example.readData();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 5; i++) {
                example.writeData(i);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread reader1 = new Thread(readTask, "Reader-1");
        Thread reader2 = new Thread(readTask, "Reader-2");
        Thread writer1 = new Thread(writeTask, "Writer-1");

        reader1.start();
        reader2.start();
        writer1.start();
    }
}
