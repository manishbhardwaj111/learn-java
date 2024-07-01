package org.learn.thread;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationInStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // This will cause ConcurrentModificationException
        numbers.stream().forEach(number -> {
            if (number == 3) {
                numbers.add(5);
              //  numbers.remove(Integer.valueOf(2));
            }
        });
    }
}
