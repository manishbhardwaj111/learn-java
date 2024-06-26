package org.learn.algorithms;

import java.util.List;
import java.util.stream.Collectors;

public class TestHackerRank {

    public static void main(String[] args) {
        List<Integer> arr = List.of(256741038, 623958417, 467905213, 714532089, 938071625);
        miniMaxSum(arr);
    }

    public static void miniMaxSum(List<Integer> arr) {
        arr = arr.stream().sorted().collect(Collectors.toList());
        long max = arr.stream().reduce(0, Integer::sum);
        System.out.println(max);
        long min = max - arr.getLast();
        max = max - arr.getFirst();
        System.out.println(min + " " + max);
    }
}
