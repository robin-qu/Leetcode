// DP O(mn)time O(m)space
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        
        int n = coins.length;
        int[] dp = new int[amount + 1];  // dp[i] represent the minimum number of coins needed to make up amount i, dp[i] = -1 if not possible
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != -1) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
            
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        
        return dp[amount];
    }
}