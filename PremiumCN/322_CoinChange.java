// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         if (coins == null || coins.length == 0) {
//             return -1;
//         }

//         int n = coins.length;

//         int[][] dp = new int[n + 1][amount + 1];
//         Arrays.fill(dp[0], Integer.MAX_VALUE);
//         dp[0][0] = 0;
//         for (int i = 1; i <= n; i++) {
//             Arrays.fill(dp[i], Integer.MAX_VALUE);
//             dp[i][0] = 0;
//             for (int j = 1; j <= amount; j++) {
//                 dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
//                 if (j >= coins[i -  1] && dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
//                     dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
//                 }
//             }
//         }
        
//         return dp[n][amount] == Integer.MAX_VALUE ? -1 : dp[n][amount];
//     }
// }


class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int n = coins.length;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i -  1] && dp[j - coins[i - 1]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}