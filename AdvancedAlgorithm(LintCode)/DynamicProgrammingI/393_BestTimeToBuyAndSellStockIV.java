// // DP O(K) space (OutOfMemoryError)
// public class Solution {
//     /**
//      * @param K: An integer
//      * @param prices: An integer array
//      * @return: Maximum profit
//      */
//     public int maxProfit(int K, int[] prices) {
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }
        
//         int n = prices.length;
        
//         // tackle the corner case
//         if (K > n / 2) {
//             return quickSolve(prices);
//         }
        
//         int[] dp = new int[2 * K + 1]; // odd indices are buy, even indices are sell
//         for (int i = 1; i <= 2 * K; i += 2) {
//             dp[i] = -prices[0];
//         }
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 1; j <= 2 * K; j++) {
//                 if (j % 2 == 1) {
//                     dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);  // buy
//                 } else {
//                     dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);  // sell
//                 }
                
//             }
//         }
        
//         return dp[2 * K];
//     }
    
//     private int quickSolve(int[] prices) {
//         int max = 0;
//         for (int i = 1; i < prices.length; i++) {
//             if (prices[i] > prices[i - 1]) {
//                 max += prices[i] - prices[i - 1];
//             }
//         }
        
//         return max;
//     }
// }


// DP O(K) space
public class Solution {
    /**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        // tackle the corner case
        if (K > n / 2) {
            return quickSolve(prices);
        }
        
        int[][] dp = new int[2][K + 1];
        for (int i = 0; i <= K; i++) {
            dp[0][i] = -prices[0];
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= K; j++) {
                dp[0][j] = Math.max(dp[0][j], dp[1][j - 1] - prices[i]);  // buy
                dp[1][j] = Math.max(dp[1][j], dp[0][j] + prices[i]);  // sell
            }
        }
        
        return dp[1][K];
    }
    
    private int quickSolve(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        
        return max;
    }
}