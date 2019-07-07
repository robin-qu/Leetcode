// // DP O(n) space
// public class Solution {
//     /**
//      * @param str: a string
//      * @return: the length of the longest repeating subsequence
//      */
//     public int longestRepeatingSubsequence(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         int[][] dp = new int[n + 1][n + 1];  // dp[i][j] represents the longest repeating subsequence of first i character and first j character
        
//         for (int i = 1; i <= n; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
//                     dp[i][j] = dp[i - 1][j - 1] + 1;
//                 } else {
//                     dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
//                 }
//             }
//         }
        
//         return dp[n][n];
//     }
// }


// DP O()1 space
public class Solution {
    /**
     * @param str: a string
     * @return: the length of the longest repeating subsequence
     */
    public int longestRepeatingSubsequence(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[][] dp = new int[2][n + 1];  // dp[i][j] represents the longest repeating subsequence of first i character and first j character
        int old = 1;
        int now = 0;
        
        for (int i = 1; i <= n; i++) {
            now = old;
            old = 1 - now;
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[now][j] = dp[old][j - 1] + 1;
                } else {
                    dp[now][j] = Math.max(dp[now][j - 1], dp[old][j]);
                }
            }
        }
        
        return dp[now][n];
    }
}