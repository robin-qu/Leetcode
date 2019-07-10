// public class Solution {
//     /**
//      * @param word1: A string
//      * @param word2: A string
//      * @return: The minimum number of steps.
//      */
//     public int minDistance(String word1, String word2) {
//         if (word1 == null || word2 == null) {
//             return 0;
//         }
        
//         char[] w1 = word1.toCharArray();
//         char[] w2 = word2.toCharArray();
//         int m = w1.length;
//         int n = w2.length;
        
//         int[][] dp = new int[m + 1][n + 1];  // dp[i][j] represents the minimum edit distance of the first i characters of word1 and the first j characters of word2
        
//         for (int i = 0; i <= m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 if (i == 0) {
//                     dp[i][j] = j;
//                     continue;
//                 }
                
//                 if (j == 0) {
//                     dp[i][j] = i;
//                     continue;
//                 }
                
//                 if (w1[i - 1] == w2[j - 1]) {
//                     dp[i][j] = dp[i - 1][j - 1];
//                 } else {
//                     dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
//                     // corresponds to replace, add, delete respectively
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// Space optimization
public class Solution {
    /**
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int m = w1.length;
        int n = w2.length;
        
        int[][] dp = new int[2][n + 1];  // dp[i][j] represents the minimum edit distance of the first i characters of word1 and the first j characters of word2
        int old = 0;
        int now = 1;
        
        for (int i = 0; i <= m; i++) {
            now = old;
            old = 1 - now;
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[now][j] = j;
                    continue;
                }
                
                if (j == 0) {
                    dp[now][j] = i;
                    continue;
                }
                
                if (w1[i - 1] == w2[j - 1]) {
                    dp[now][j] = dp[old][j - 1];
                } else {
                    dp[now][j] = 1 + Math.min(Math.min(dp[old][j - 1], dp[now][j - 1]), dp[old][j]);
                    // corresponds to replace, add, delete respectively
                }
            }
        }
        
        return dp[now][n];
    }
}