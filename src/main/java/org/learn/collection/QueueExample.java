package org.learn.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        // Create a queue using LinkedList
        Queue<String> queue = new LinkedList<>();

        // Add elements to the queue
        queue.add("Element 1");
        queue.add("Element 2");
        queue.add("Element 3");

        // Display the queue
        System.out.println("Queue: " + queue);

        // Remove an element from the queue
        String removedElement = queue.remove();
        System.out.println("Removed Element: " + removedElement);

        // Display the queue after removal
        System.out.println("Queue after removal: " + queue);

        // Peek at the head of the queue
        String headElement = queue.poll();
        System.out.println("Head of the Queue: " + headElement);

        // Check the size of the queue
        int size = queue.size();
        System.out.println("Queue size: " + size);

        // Check if the queue is empty
        boolean isEmpty = queue.isEmpty();
        System.out.println("Is the queue empty? " + isEmpty);
    }
}
