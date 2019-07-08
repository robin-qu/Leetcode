// // O(n^3) TLE
// public class Solution {
//     /**
//      * @param prices: a list of integers
//      * @param fee: a integer
//      * @return: return a integer
//      */
//     public int maxProfit(int[] prices, int fee) {
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }
        
//         int n = prices.length;
        
//         int[][] dp = new int[n][n + 1];
//         int res = 0;
        
//         for (int k = 1; k <= n / 2; k++) {
//             // initialization
//             Arrays.fill(dp[0], 0);
//             for (int i = 1; i <= 2 * k; i += 2) {
//                 dp[0][i] = -prices[0] - fee;
//             }
            
//             for (int i = 1; i < n; i++) {
//                 for (int j = 1; j <= 2 * k; j++) {
//                     if (j % 2 == 1) {  // buy
//                         dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1] - prices[i] - fee);
//                     } else {
//                         dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1] + prices[i]);
//                     }
//                 }
//             }
            
//             res = Math.max(res, dp[n - 1][2 * k]);
//         }
        
//         return res;
//     }
// }


// DP O(n)
public class Solution {
    /**
     * @param prices: a list of integers
     * @param fee: a integer
     * @return: return a integer
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        int hold = -prices[0]; // maximum profit when some stock is held
        int sell = 0; // maximum profit when there is no stock in hands
        
        for (int i = 1; i < n; i++) {
            hold = Math.max(hold, sell - prices[i]);
            sell = Math.max(sell, hold + prices[i] - fee);
        }
        
        return sell;
    }
}