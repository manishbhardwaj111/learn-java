package org.learn.thread;

public class VirtualThreadExample {
    public static void main(String[] args) {
        // Creating a virtual thread
        Thread virtualThread = Thread.ofVirtual().start(
                () -> System.out.println("Hello from virtual thread: " + Thread.currentThread())
        );

        // Creating multiple virtual threads
        for (int i = 0; i < 10; i++) {
            Thread.ofVirtual().start(() -> {
                System.out.println("Running in virtual thread: " + Thread.currentThread());
                try {
                    Thread.sleep(1000); // Simulating some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Main thread waits for virtual thread to complete
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Main thread sleeps to allow other virtual threads to complete their work
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread exiting.");
    }
}
