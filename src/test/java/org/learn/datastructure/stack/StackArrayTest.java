package org.learn.datastructure.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StackArrayTest {

    @Test
    void testIsEmptyOnNewStack() {
        StackArray<Integer> stack = new StackArray<>(5);
        assertTrue(stack.isEmpty());
        assertFalse(stack.isFull());
    }

    @Test
    void testIsFullWhenStackReachesCapacity() {
        StackArray<Integer> stack = new StackArray<>(2);
        stack.push(10);
        stack.push(20);
        assertTrue(stack.isFull());
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPushAndPeek() {
        StackArray<String> stack = new StackArray<>(3);
        stack.push("A");
        stack.push("B");
        assertEquals("B", stack.peek());
    }

    @Test
    void testPushAndPop() {
        StackArray<Integer> stack = new StackArray<>(3);
        stack.push(100);
        stack.push(200);
        assertEquals(200, stack.pop());
        assertEquals(100, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPushBeyondCapacityThrowsException() {
        StackArray<Integer> stack = new StackArray<>(1);
        stack.push(1);
        Exception ex = assertThrows(IllegalStateException.class, () -> stack.push(2));
        assertEquals("Stack is full", ex.getMessage());
    }

    @Test
    void testPopOnEmptyStackThrowsException() {
        StackArray<Integer> stack = new StackArray<>(2);
        Exception ex = assertThrows(IllegalStateException.class, stack::pop);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    void testPeekOnEmptyStackThrowsException() {
        StackArray<String> stack = new StackArray<>(2);
        Exception ex = assertThrows(IllegalStateException.class, stack::peek);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    void testMultiplePushPop() {
        StackArray<Integer> stack = new StackArray<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());
        stack.push(4);
        assertEquals(4, stack.peek());
        assertFalse(stack.isEmpty());
    }
}
