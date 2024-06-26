package org.learn.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryUsageExample {
    public static void main(String[] args) {
        // Get the memory MXBean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // Get the heap memory usage
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        // Convert bytes to GB
        double usedGB = bytesToGB(heapMemoryUsage.getUsed());
        double maxGB = bytesToGB(heapMemoryUsage.getMax());

        // Calculate the percentage of heap memory used
        double percentUsed = (double) heapMemoryUsage.getUsed() / heapMemoryUsage.getMax() * 100;

        // Print memory usage information with 3 decimal places
        System.out.println("Heap Memory:");
        System.out.printf("   Used: %.3f GB%n", usedGB);
        System.out.printf("   Max:  %.3f GB%n", maxGB);
        System.out.printf("   Percent Used: %.3f%%%n", percentUsed);
    }

    // Helper method to convert bytes to gigabytes (GB)
    private static double bytesToGB(long bytes) {
        return (double) bytes / (1024 * 1024 * 1024);
    }
}
