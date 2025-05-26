package org.learn.datastructure.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueArrayTest {

    private QueueArray<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new QueueArray<>(3); // capacity of 3
    }

    // isEmpty tests
    @Test
    void testIsEmptyInitially() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterEnqueue() {
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
    }

    // isFull tests
    @Test
    void testIsFullInitially() {
        assertFalse(queue.isFull());
    }

    @Test
    void testIsFullAfterEnqueue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.isFull());
    }

    // enqueue tests
    @Test
    void testEnqueueSuccessfully() {
        queue.enqueue(5);
        assertEquals(5, queue.peek());
    }

    @Test
    void testEnqueueWhenFullThrowsException() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Exception exception = assertThrows(IllegalStateException.class, () -> queue.enqueue(4));
        assertEquals("Queue is full", exception.getMessage());
    }

    // dequeue tests
    @Test
    void testDequeueSuccessfully() {
        queue.enqueue(100);
        queue.enqueue(200);
        assertEquals(100, queue.dequeue());
        assertEquals(200, queue.dequeue());
    }

    @Test
    void testDequeueWhenEmptyThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> queue.dequeue());
        assertEquals("Queue is empty", exception.getMessage());
    }

    // peek tests
    @Test
    void testPeekSuccessfully() {
        queue.enqueue(42);
        assertEquals(42, queue.peek());
        assertFalse(queue.isEmpty()); // Peek should not remove
    }

    @Test
    void testPeekWhenEmptyThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> queue.peek());
        assertEquals("Queue is empty", exception.getMessage());
    }
}