package org.learn.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            threadPool.submitTask(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        threadPool.shutdown();
    }

    static class CustomThreadPool {
        private final int poolSize;
        private final WorkerThread[] workers;
        private final BlockingQueue<Runnable> taskQueue;

        public CustomThreadPool(int poolSize) {
            this.poolSize = poolSize;
            taskQueue = new LinkedBlockingQueue<>();
            workers = new WorkerThread[poolSize];

            for (int i = 0; i < poolSize; i++) {
                workers[i] = new WorkerThread(taskQueue);
                workers[i].start();
            }
        }

        public void submitTask(Runnable task) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void shutdown() {
            for (WorkerThread worker : workers) {
                worker.interrupt();
            }
        }
    }

    static class WorkerThread extends Thread {
        private final BlockingQueue<Runnable> taskQueue;

        public WorkerThread(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
