package org.learn.datastructure.recursion;

public class RecursionExample {
    public static void main(String[] args) {
        System.out.println(digitSum(12342));
    }

    // Recursive function to calculate the sum of digits of a number
    public static int digitSum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }
        if (n < 10) {
            return n; // Base case: single-digit number
        }
        return (n % 10) + digitSum(n / 10); // Recursive case
    }

    // find gcd of two numbers using recursion
    public static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    // write function to convert decimal to binary using recursion
   public static String decimalToBinary(int n) {
       if (n < 0) {
           throw new IllegalArgumentException("Input must be a non-negative integer.");
       }
       if (n == 0) {
           return "0"; // Special case: 0 in binary is "0"
       }
       return convertToBinary(n).toString(); // Helper method for recursion
   }

   private static StringBuilder convertToBinary(int n) {
       if (n == 0) {
           return new StringBuilder(); // Base case: return empty for recursion
       }
       return convertToBinary(n / 2).append(n % 2); // Recursive case
   }

}
