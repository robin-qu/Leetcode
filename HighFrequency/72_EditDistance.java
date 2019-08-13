// // DP O(mn)time O(mn)space
// class Solution {
//     public int minDistance(String word1, String word2) {
//         if (word1 == null || word2 == null) {
//             return 0;
//         }
        
//         int m = word1.length();
//         int n = word2.length();
//         int[][] dp = new int[m + 1][n + 1];  //dp[i][j] represents the minimum edit distances between the first i characters in word1 and first j characters in word2
//         dp[0][0] = 0;
//         for (int i = 1; i <= m; i++) {
//             dp[i][0] = i;
//         }
//         for (int i = 1; i <= n; i++) {
//             dp[0][i] = i;
//         }
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j - 1];  // ended with same character
//                 } else {
//                     dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])); // add  remove  replace
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }

// DP O(mn)time O(n)space 1D array
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n + 1];  //dp[i][j] represents the minimum edit distances between the first i characters in word1 and first j characters in word2
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;  // ended with same character
                } else {
                    dp[j] = 1 + Math.min(dp[j - 1], Math.min(dp[j], prev)); // add  remove  replace
                }
                
                prev = temp;
            }
        }
        
        return dp[n];
    }
}