package org.learn.thread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");

        System.out.println("Initial List: " + list);

        // Iterate and modify concurrently
        for (String name : list) {
            System.out.println("Current Element: " + name);
            if (name.equals("Bob")) {
                list.add("David");
            }
        }

        System.out.println("List after iteration: " + list);
    }
}
