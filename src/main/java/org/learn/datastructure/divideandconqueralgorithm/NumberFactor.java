package org.learn.datastructure.divideandconqueralgorithm;


// Given N, find the number of ways to express N as a sum of 1,3 and 4

public class NumberFactor {

    public static int waysToGet(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        return waysToGet(n - 1) + waysToGet(n - 3)
                + waysToGet(n - 4);
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Number of ways to get " + n + " : " + waysToGet(n));
        System.out.println("Number of ways to get " + n + " : " + waysToGetDPTD(n));
        System.out.println("Number of ways to get " + n + " : " + waysToGetDPBU(n));
        System.out.println(waysToGet(n - 1));
        System.out.println(waysToGet(n - 3));
        System.out.println(waysToGet(n - 4));
    }

    public static int waysToGetDPBU(int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1 || n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[n - 4];
        }
        return dp[n];
    }

    public static int waysToGetDPTD(int[] dp, int n) {
        if (n < 0) return 0;
        if (n == 0 || n == 1 || n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (dp[0] == 0) {
            int sp1 = waysToGetDPTD(dp, n - 1);
            int sp2 = waysToGetDPTD(dp, n - 3);
            int sp3 = waysToGetDPTD(dp, n - 4);
            dp[n] = sp1 + sp2 + sp3;
        }
        return dp[n];
    }

    public static int waysToGetDPTD(int n) {
        return waysToGetDPTD(new int[n + 1], n);
    }
}