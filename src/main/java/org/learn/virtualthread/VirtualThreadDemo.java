package org.learn.virtualthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        // Create a virtual thread executor service
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            // Submit a task to the virtual thread executor
            executorService.submit(() -> {
                System.out.println("Running in a virtual thread");
                System.out.println(Thread.ofVirtual());
            });

            // Shutdown the executor service
            executorService.shutdown();
        }
        Thread.sleep(1000);
    }
}
