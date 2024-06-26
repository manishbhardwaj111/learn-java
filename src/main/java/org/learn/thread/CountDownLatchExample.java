package org.learn.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numTasks = 3;
        CountDownLatch latch = new CountDownLatch(numTasks);

        for (int i = 0; i < numTasks; i++) {
            new Thread(new Task(latch)).start();
        }

        latch.await(); // wait for all tasks to complete
        System.out.println("All tasks completed. Proceeding with main thread.");
    }

    static class Task implements Runnable {
        private final CountDownLatch latch;

        Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is working.");
                Thread.sleep(2000); // simulate work
                System.out.println(Thread.currentThread().getName() + " completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }
    }
}
