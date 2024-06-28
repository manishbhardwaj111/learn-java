package org.learn.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class CustomForkJoinPoolExample {
    public static void main(String[] args) {
        // Create a custom ForkJoinPool with a specific parallelism level
        ForkJoinPool customThreadPool = new ForkJoinPool(4);

        // Get the number of available processors
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available processors: " + processors);

        // Use the custom pool to execute a parallel stream operation
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,45,45
                ,45,454,5,4,55,4545,445,4,545,54,54,45,54455454,54,54,54);

        customThreadPool.submit(() -> {
            numbers.parallelStream().forEach(num -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Processing number " + num + " in thread " + threadName);
                int processorss = Runtime.getRuntime().availableProcessors();
                System.out.println("Number of available processors: " + processorss);
            });
        }).join();

        customThreadPool.shutdown();
    }
}
