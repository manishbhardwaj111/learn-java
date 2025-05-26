package org.learn.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // Initialize with 3 parties
        CyclicBarrier barrier = new CyclicBarrier(3, ()->System.out.println("Barrier reached common points"));

        // T1, T2, and T3 perform tasks and await at the barrier
        Thread T1 = new Thread(() -> {
            System.out.println("T1 doing task");
            try {
                barrier.await(); // Wait until all threads reach the barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("T1 can proceed");
        });

        Thread T2 = new Thread(() -> {
            System.out.println("T2 doing task");
            try {
                barrier.await(); // Wait until all threads reach the barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("T2 can proceed");
        });

        Thread T3 = new Thread(() -> {
            System.out.println("T3 doing task");
            try {
                barrier.await(); // Wait until all threads reach the barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("T3 can proceed");
        });

        T1.start();
        T2.start();
        T3.start();
        System.out.println("Main Task completed");
    }
}
