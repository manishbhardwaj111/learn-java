package org.learn.thread;

import java.util.LinkedList;
import java.util.List;

public class CustomThreadPoolWithLinkedList {
    private final List<Worker> workers;
    private final LinkedList<Runnable> taskQueue;
    private volatile boolean isShutdown = false;

    public CustomThreadPoolWithLinkedList(int numThreads) {
        workers = new LinkedList<>();
        taskQueue = new LinkedList<>();
        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            System.out.println(isShutdown + " : " +taskQueue.size());
            if (isShutdown) {
                throw new IllegalStateException("Thread pool is shutdown, cannot accept new tasks.");
            }
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void shutdown() {
        System.out.println("shutdown called");
        isShutdown = true;
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (isShutdown && taskQueue.isEmpty()) {
                        break;
                    }
                    task = taskQueue.poll();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolWithLinkedList pool = new CustomThreadPoolWithLinkedList(3);

        for (int i = 0; i < 1000; i++) {
            final int taskID = i;
            pool.execute(() -> {
                System.out.println("Executing task " + taskID + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (taskID == 50){
                    pool.shutdown();
                }
            });

        }
        pool.shutdown();
    }
}
