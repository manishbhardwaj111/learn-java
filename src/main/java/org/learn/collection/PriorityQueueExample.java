package org.learn.collection;

import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Creating a PriorityQueue with natural ordering (min-heap)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Adding elements to the PriorityQueue
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(15);
        priorityQueue.add(5);

        // Displaying the PriorityQueue
        System.out.println("PriorityQueue: " + priorityQueue);

        // Removing elements from the PriorityQueue (poll() removes the head of the queue)
        System.out.println("Removed: " + priorityQueue.poll()); // 5
        System.out.println("Removed: " + priorityQueue.poll()); // 10

        // Displaying the PriorityQueue after removals
        System.out.println("PriorityQueue after removals: " + priorityQueue);

        // Peeking at the next element (peek() returns the head but does not remove it)
        System.out.println("Next element: " + priorityQueue.peek()); // 15
    }
}
