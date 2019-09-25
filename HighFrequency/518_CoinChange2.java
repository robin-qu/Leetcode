// // dp backpack O(mn)time O(mn)space
// class Solution {
//     public int change(int amount, int[] coins) {
//         if (coins == null) {
//             return 0;
//         }
        
//         int n = coins.length;
        
//         int[][] dp = new int[n + 1][amount + 1];
//         dp[0][0] = 1;
//         for (int i = 1; i <= amount; i++) {
//             dp[0][i] = 0;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             dp[i][0] = 1;
//         }
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 1; j <= amount; j++) {
//                 dp[i][j] = dp[i - 1][j];  // do not add the current coin
                
//                 if (coins[i - 1] <= j) {
//                     dp[i][j] += dp[i][j - coins[i - 1]];
//                 }
//             }
//         }
        
//         return dp[n][amount];
//     }
// }


// dp backpack O(mn)time O(mn)space
class Solution {
    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        
        int n = coins.length;
        
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            dp[i] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    dp[j] += dp[j - coins[i - 1]];
                }
            }
        }
        
        return dp[amount];
    }
}