package org.learn.datastructure.divideandconqueralgorithm;

public class HouseRobber {

    public static int rob(int[] wealth) {
        int n = wealth.length;
        if (n == 0) return 0;
        if (n == 1) return wealth[0];
        int[] dp = new int[n];
        dp[0] = wealth[0];
        dp[1] = Math.max(wealth[0], wealth[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], wealth[i] + dp[i-2]);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("Max amount robbed: " + rob(houses));  // Output: 12
    }

}



























