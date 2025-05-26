package org.learn.generics;

import java.util.Arrays;
import java.util.List;

public class CollectionLearn {
    public static void main(String[] args) {
        List ints = Arrays.asList(1, 2);
        List list = ints;
        list.add(3.14);
    }
}
