package org.learn.datastructure.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArraysProblem {
    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{-2, -4, -3, 5, 7, -8, -1, 6}, -9));
    }

    // to find all pairs of integers in an array whose sum is equal to a given number
    public static List<List<Integer>> findPairs(int[] arr, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int target = targetSum - num;
            if (seen.contains(target)) {
                System.out.printf("[%s, %s]%n", target, num);
                // Add the pair to the result list
                result.add(List.of(target, num));
            }
            seen.add(num);
        }
        return result;
    }
}
