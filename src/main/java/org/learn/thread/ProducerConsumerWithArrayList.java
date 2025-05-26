package org.learn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithArrayList {
    public static void main(String[] args) {
        SharedList sharedList = new SharedList(5); // Set maximum capacity to 5
        new Thread(new Producer(sharedList)).start();
        new Thread(new Consumer(sharedList)).start();
    }
}

class SharedList {
    private final List<String> list = new ArrayList<>();
    private final int maxCapacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public SharedList(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void add(String record) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == maxCapacity) {
                notFull.await();
            }
            list.add(record);
            System.out.println("List after adding: " + list);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String remove() throws InterruptedException {
        lock.lock();
        try {
            while (list.isEmpty()) {
                notEmpty.await();
            }
            String record = list.removeFirst();
            System.out.println("List after removing: " + list);
            notFull.signal();
            return record;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private final SharedList sharedList;

    public Producer(SharedList sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                String record = "Record " + i++;
                sharedList.add(record);
                System.out.println("Produced: " + record);
            //    Thread.sleep(500); // Simulate time taken to produce a record
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedList sharedList;

    public Consumer(SharedList sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String record = sharedList.remove();
                System.out.println("Consumed: " + record);
                // Simulate time taken to process a record
             //   Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
