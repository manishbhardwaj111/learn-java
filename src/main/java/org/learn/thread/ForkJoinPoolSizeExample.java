package org.learn.thread;

import java.util.Arrays;
import java.util.List;

public class ForkJoinPoolSizeExample {
    public static void main(String[] args) {
        // Get the number of available processors
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available processors: " + processors);

        // Set the system property for custom parallelism level
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

        processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of available processors: " + processors);

        // Run a simple parallel stream operation
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,45,45
                ,45,454,5,4,55,4545,445,4,545,54,54,45,54455454,54,54,54);
        numbers.parallelStream().forEach(num -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Processing number " + num + " in thread " + threadName);
        });
    }
}
