// // DP O(mn^2)time O(nm)space  initial version
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s == null || p == null) {
//             return false;
//         }
        
//         int n = s.length();
//         int m = p.length();
//         char[] ss = s.toCharArray();
//         char[] pp = p.toCharArray();
        
//         boolean[][] dp = new boolean[m + 1][n + 1];  // dp[i][j] represents whether the first i characters in p match the first m characters in s
//         dp[0][0] = true;
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 if (j == 0) {
//                     dp[i][j] = dp[i - 1][0] && pp[i - 1] == '*';
//                     continue;
//                 }
                
//                 if (pp[i - 1] == '?') {
//                     dp[i][j] = dp[i - 1][j - 1];
//                 } else if (pp[i - 1] == '*') {
//                     if (i == 1) {
//                         dp[i][j] = true;
//                     } else {
//                         for (int k = j; k >= 0; k--) {
//                             dp[i][j] = dp[i][j] || dp[i - 1][k];
//                         }
//                     }                    
//                 } else {
//                     if (pp[i - 1] == ss[j - 1]) {
//                         dp[i][j] = dp[i - 1][j - 1];
//                     }
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// // DP O(mn)time O(nm)space
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s == null || p == null) {
//             return false;
//         }
        
//         int n = s.length();
//         int m = p.length();
//         char[] ss = s.toCharArray();
//         char[] pp = p.toCharArray();
        
//         boolean[][] dp = new boolean[m + 1][n + 1];  // dp[i][j] represents whether the first i characters in p match the first m characters in s
//         dp[0][0] = true;
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 0; j <= n; j++) {
//                 if (j == 0) {
//                     dp[i][j] = dp[i - 1][0] && pp[i - 1] == '*';
//                     continue;
//                 }
                
//                 if (pp[i - 1] == '?') {
//                     dp[i][j] = dp[i - 1][j - 1];
//                 } else if (pp[i - 1] == '*') {
//                     dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                 } else {
//                     dp[i][j] = pp[i - 1] == ss[j - 1] && dp[i - 1][j - 1];
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// DP O(mn)time O(n)space
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int n = s.length();
        int m = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
        boolean[] dp = new boolean[n + 1];  // dp[i][j] represents whether the first i characters in p match the first m characters in s
        dp[0] = true;
        
        for (int i = 1; i <= m; i++) {
            boolean prev = dp[0];
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    dp[0] = dp[0] && pp[i - 1] == '*';
                    continue;
                }
                
                if (pp[i - 1] == '?') {
                    boolean temp = dp[j];
                    dp[j] = prev;
                    prev = temp;
                } else if (pp[i - 1] == '*') {
                    dp[j] = dp[j - 1] || dp[j];
                } else {
                    boolean temp = dp[j];
                    dp[j] = pp[i - 1] == ss[j - 1] && prev;
                    prev = temp;
                }
            }
        }
        
        return dp[n];
    }
}