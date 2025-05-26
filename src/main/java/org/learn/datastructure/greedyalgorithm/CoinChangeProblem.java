package org.learn.datastructure.greedyalgorithm;

import java.util.Arrays;

public class CoinChangeProblem {
    public static void main(String[] args) {
        System.out.println(coinChangeProblem(new int[] {1, 2, 5, 10, 20, 50, 100, 500, 1000}, 90));
    }

    public static int coinChangeProblem1(int[] array, int amount) {
        int coinCount = 0;
        int currentAmount = amount;
        for (int i = array.length -1; i>= 0; i--) {
            int currency = array[i];
            if (currency <= currentAmount) {
                coinCount += currentAmount / currency;
                currentAmount = currentAmount % currency;
            }
        }
        if (currentAmount > 0) {
            System.out.println("currentAmount = " + currentAmount);
            return -1;
        } else {
            return coinCount;
        }
    }

    public static int coinChangeProblem(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        final int MAX = amount + 1;
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == MAX ? -1 : dp[amount];
    }
}
