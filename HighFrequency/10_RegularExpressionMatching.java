// // DP O(mn)time O(mn)space
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s == null || p == null) {
//             return false;
//         }
        
//         int m = s.length();
//         int n = p.length();
//         char[] ss = s.toCharArray();
//         char[] pp = p.toCharArray();
        
//         boolean[][] dp = new boolean[m + 1][n + 1];  // dp[i][j] represents whether the first i characters of s and the first j characters fo p match
//         dp[0][0] = true;
//         for (int i = 1; i <= n; i++) {
//             if (pp[i - 1] == '*') {
//                 dp[0][i] = dp[0][i - 2];
//             }
//         }
        
//         for (int i = 1; i <= m; i++) {
//             for (int j = 1; j <= n; j++) {
//                 if (pp[j - 1] == '*') {
//                     if (dp[i][j - 2]) {
//                         dp[i][j] = true;  // '*' counts as empty, repeat 0 time
//                         continue;
//                     }
//                     if (pp[j - 2] == ss[i - 1] || pp[j - 2] == '.') {
//                         dp[i][j] = dp[i - 1][j];  // repeat as least 1 times
//                     }
//                 } else if (pp[j - 1] == '.') {
//                     dp[i][j] = dp[i - 1][j - 1];
//                 } else {
//                     dp[i][j] = dp[i - 1][j - 1] && ss[i - 1] == pp[j - 1];
//                 }
//             }
//         }
        
//         return dp[m][n];
//     }
// }


// // DP O(mn)time O(mn)space
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s == null || p == null) {
//             return false;
//         }
        
//         int m = s.length();
//         int n = p.length();
//         char[] ss = s.toCharArray();
//         char[] pp = p.toCharArray();
        
//         boolean[][] dp = new boolean[2][n + 1];  // dp[i][j] represents whether the first i characters of s and the first j characters fo p match
//         dp[0][0] = true;
//         for (int i = 1; i <= n; i++) {
//             if (pp[i - 1] == '*') {
//                 dp[0][i] = dp[0][i - 2];
//             }
//         }
        
//         int now = 0;
//         int old = 1;
//         for (int i = 1; i <= m; i++) {
//             old = now;
//             now = 1 - now;
//             Arrays.fill(dp[now], false);
//             for (int j = 1; j <= n; j++) {
//                 if (pp[j - 1] == '*') {
//                     if (dp[now][j - 2]) {
//                         dp[now][j] = true;  // '*' counts as empty, repeat 0 time
//                         continue;
//                     }
//                     if (pp[j - 2] == ss[i - 1] || pp[j - 2] == '.') {
//                         dp[now][j] = dp[old][j];  // repeat as least 1 times
//                     }
//                 } else if (pp[j - 1] == '.') {
//                     dp[now][j] = dp[old][j - 1];
//                 } else {
//                     dp[now][j] = dp[old][j - 1] && ss[i - 1] == pp[j - 1];
//                 }
//             }
//         }
        
//         return dp[now][n];
//     }
// }



// DP O(mn)time O(mn)space  1D array
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        int m = s.length();
        int n = p.length();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        
        boolean[] dp = new boolean[n + 1];  // dp[i][j] represents whether the first i characters of s and the first j characters fo p match
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            if (pp[i - 1] == '*') {
                dp[i] = dp[i - 2];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            boolean prev = dp[0];
            dp[0] = false;
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (pp[j - 1] == '*') {
                    if (dp[j - 2]) {
                        dp[j] = true;  // '*' counts as empty, repeat 0 time
                    } else if (pp[j - 2] == ss[i - 1] || pp[j - 2] == '.') {
                        dp[j] = dp[j];  // repeat as least 1 times
                    } else {
                        dp[j] = false;
                    }
                } else if (pp[j - 1] == '.') {
                    dp[j] = prev;
                } else {
                    dp[j] = prev && ss[i - 1] == pp[j - 1];
                }
                prev = temp;
            }
        }
        
        return dp[n];
    }
}
