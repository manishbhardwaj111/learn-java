package org.learn.datastructure.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StackLinkedListTest {

    @Test
    void testIsEmptyOnNewStack() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIsFullWhenStackReachesCapacity() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(10);
        stack.push(20);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPushAndPeek() {
        StackLinkedList<String> stack = new StackLinkedList<>();
        stack.push("A");
        stack.push("B");
        assertEquals("B", stack.peek());
    }

    @Test
    void testPushAndPop() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(100);
        stack.push(200);
        assertEquals(200, stack.pop());
        assertEquals(100, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopOnEmptyStackThrowsException() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        Exception ex = assertThrows(IllegalStateException.class, stack::pop);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    void testPeekOnEmptyStackThrowsException() {
        StackLinkedList<String> stack = new StackLinkedList<>();
        Exception ex = assertThrows(IllegalStateException.class, stack::peek);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    void testMultiplePushPop() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
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
