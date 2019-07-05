// // O(n^2) space
// public class Solution {
//     /**
//      * @param values: a vector of integers
//      * @return: a boolean which equals to true if the first player will win
//      */
//     public boolean firstWillWin(int[] values) {
//         if (values == null || values.length == 0) {
//             return true;
//         }
        
//         int n = values.length;
//         int[][] dp = new int[n][n];
        
//         for (int len = 0; len < n; len++) {
//             for (int left = 0; left + len < n; left++) {  // start position
//                 int right = left + len;  // end position
//                 if (len == 0) {
//                     dp[left][right] = values[left];
//                 } else {
//                     dp[left][right] = Math.max(values[left] - dp[left + 1][right], values[right] - dp[left][right - 1]);
//                 }
//             }
//         }
        
//         return dp[0][n - 1] >= 0;
//     }
// }


// Reduce space complexity to O(n)
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return true;
        }
        
        int n = values.length;
        int[] dp = new int[n];
        
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {  // start position
                if (len == 0) {
                    dp[i] = values[i];
                } else {
                    dp[i] = Math.max(values[i] - dp[i + 1], values[i + len] - dp[i]);
                }
            }
        }
        
        return dp[0] >= 0;
    }
}