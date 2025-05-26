package org.learn.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2); // Initialize with 2

        // T1 and T2 perform tasks and call countDown()
        Thread T1 = new Thread(() -> {
            System.out.println("T1 doing task");
            latch.countDown(); // Decrement count
            System.out.println("T1 completed task");
        });

        Thread T2 = new Thread(() -> {
            System.out.println("T2 doing task");
            latch.countDown(); // Decrement count
            System.out.println("T2 completed task");
        });

        // T3 waits for T1 and T2 to finish
        Thread T3 = new Thread(() -> {
            try {
                latch.await(); // Wait until count reaches zero
                System.out.println("T3 can proceed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        T1.start();
        T2.start();
        T3.start();
    }
}
