// // dp O(mn)time O(mn)space
// public class Solution {
//     /**
//      * @param S: A string
//      * @param T: A string
//      * @return: Count the number of distinct subsequences
//      */
//     public int numDistinct(String S, String T) {
//         if (S == null || T == null) {
//             return 0;
//         }
        
//         int m = S.length();
//         int n = T.length();
        
//         int[][] dp = new int[m + 1][n + 1];  // dp[i][j] represents the number of distinct subsequences of the first i characters of S equals the first j characters of T
        
//         // initialization
//         for (int i = 0; i <= m; i++) {
//             dp[i][0] = 1;
//         }
        
//         for (int j = 1; j <= n; j++) {  // j-th character in T
//             for (int i = 1; i <= m; i++) {  // i-th character in S
//                 if (S.charAt(i - 1) == T.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
//                 } else {
//                     dp[i][j] = dp[i - 1][j];
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// dp O(mn)time O(n)space  rolling array
public class Solution {
    /**
     * @param S: A string
     * @param T: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        
        int m = S.length();
        int n = T.length();
        
        int[][] dp = new int[m + 1][2];  // dp[i][j] represents the number of distinct subsequences of the first i characters of S equals the first j characters of T
        
        // initialization
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        int old = 1;
        int now = 0;
        for (int j = 1; j <= n; j++) {  // j-th character in T
            old = now;
            now = 1 - now;
            dp[0][now] = 0;
            for (int i = 1; i <= m; i++) {  // i-th character in S
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][now] = dp[i - 1][now] + dp[i - 1][old];
                } else {
                    dp[i][now] = dp[i - 1][now];
                }
            }
        }
        
        return dp[m][now];
    }
}