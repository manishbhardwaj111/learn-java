package org.learn.collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PriorityQueueCustomComparatorExample {
    public static void main(String[] args) {
        // Custom comparator for max-heap (reverse natural ordering)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);

        // Adding elements to the PriorityQueue
        priorityQueue.add(10);
        priorityQueue.add(20);
        priorityQueue.add(15);
        priorityQueue.add(5);

        // Displaying the PriorityQueue
        System.out.println("PriorityQueue: " + priorityQueue);

        // Removing elements from the PriorityQueue (poll() removes the head of the queue)
        System.out.println("Removed: " + priorityQueue.poll()); // 20
        System.out.println("Removed: " + priorityQueue.poll()); // 15

        // Displaying the PriorityQueue after removals
        System.out.println("PriorityQueue after removals: " + priorityQueue);

        // Peeking at the next element (peek() returns the head but does not remove it)
        System.out.println("Next element: " + priorityQueue.peek()); // 10
    }
}
