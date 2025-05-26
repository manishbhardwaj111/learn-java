package org.learn.algorithms;

public class SmallestNumberGreaterThan {

    public static int findSmallestNumberGreaterThan(int[] array, int inputNumber) {
        // Initialize the smallest number greater than inputNumber to a large value
        int smallestNumber = Integer.MAX_VALUE;

        // Iterate through the array
        for (int number : array) {
            // Check if current number is greater than inputNumber
            if (number > inputNumber) {
                // Check if current number is smaller than smallestNumber found so far
                if (number < smallestNumber) {
                    smallestNumber = number;
                }
            }
        }

        // Return smallestNumber found (if updated)
        return smallestNumber;
    }

    public static void main(String[] args) {
        int[] array = {10, 5, 8, 12, 3, 15};
        int inputNumber = 70;

        int smallestGreaterNumber = findSmallestNumberGreaterThan(array, inputNumber);

        System.out.println("Smallest number greater than " + inputNumber + " in the array: " + smallestGreaterNumber);
    }
}
