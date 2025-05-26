package org.learn.algorithms;

import java.util.Arrays;

public class RearrangeOddEven {
    public static void rearrangeOddEven(int[] arr) {
        int nextOdd = 0;
        int nextEven = 0;
        while (nextEven < arr.length) {
            if (arr[nextEven] % 2 != 0) { // Check if the number is odd
                swap(arr, nextOdd, nextEven);
                nextOdd++;
            }
            nextEven++;
        }
    }

    private static void swap(int[] arr, int nextOdd, int nextEven) {
        int temp = arr[nextOdd];
        arr[nextOdd] = arr[nextEven];
        arr[nextEven] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 6, 5, 8};
        System.out.println("Original Array:" + Arrays.toString(arr));
        rearrangeOddEven(arr);
        System.out.println("Rearranged Array:" + Arrays.toString(arr));
    }
}