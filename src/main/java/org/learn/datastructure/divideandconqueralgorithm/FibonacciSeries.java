package org.learn.datastructure.divideandconqueralgorithm;

public class FibonacciSeries {
    public static void main(String[] args) {

    }

    // Time - O(2^n)
    // Space - O(1)
    public static int fibonacciSeries(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacciSeries(n-1) + fibonacciSeries(n - 2);
        }
    }


    // Time - O(n)
    // Space - O(1)
    public int fibOptimized(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}
