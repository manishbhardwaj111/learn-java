package org.learn.datastructure.dynamicprogramming;

public class Knapsack {

    // Function to solve 0/1 Knapsack using DP
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build the DP table in a bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;  // base case: 0 items or 0 capacity
                } else if (weights[i - 1] <= w) {
                    // Max of including or excluding the item
                    dp[i][w] = Math.max(
                        values[i - 1] + dp[i - 1][w - weights[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    // Cannot include the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] values = {10, 40, 50, 70};
        int capacity = 8;

        int maxValue = knapsack(capacity, weights, values, weights.length);
        System.out.println("Maximum value in knapsack = " + maxValue);
    }
}
