package org.learn.datastructure.divideandconqueralgorithm;

import java.util.Arrays;

public class ConvertString {
    // Convert S2 to S1 using delete, insert or replace operations
    public static int convertString(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // insert all of s2
                } else if (j == 0) {
                    dp[i][j] = i; // delete all of s1
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no change
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // replace
                            Math.min(dp[i][j - 1],   // insert
                                    dp[i - 1][j]));  // delete
                }
                System.out.println(Arrays.deepToString(dp));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(convertString("kitten", "sitting"));
    }
}
