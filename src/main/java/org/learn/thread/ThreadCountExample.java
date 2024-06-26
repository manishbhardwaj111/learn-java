package org.learn.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadCountExample {
    public static void main(String[] args) {
        // Get the ThreadMXBean instance
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // Get the current number of live threads including both daemon and non-daemon threads
        int threadCount = threadMXBean.getThreadCount();

        // Print the number of threads
        System.out.println("Current number of threads: " + threadCount);

        // You can also get more detailed information about each thread if needed
        long[] threadIds = threadMXBean.getAllThreadIds();
        for (long threadId : threadIds) {
            System.out.println("Thread ID: " + threadId);
        }
    }
}
