// // dp O(n^2)time O(n^2)space
// class Solution {
//     public int longestPalindromeSubseq(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         int[][] dp = new int[n][n];  // dp[i][j] represents the result of the subsequence betweenn i and j
        
//         for (int len = 1; len <= n; len++) {
//             for (int i = 0; i + len - 1 < n; i++) {
//                 int j = i + len - 1;
//                 if (len == 1) {
//                     dp[i][j] = 1;
//                 } else if (len == 2) {
//                     dp[i][j] = ss[i] == ss[j] ? 2 : 1;
//                 } else if (ss[i] == ss[j]) {
//                     dp[i][j] = 2 + dp[i + 1][j - 1];
//                 } else {
//                     dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
//                 }
//             }
//         }
        
//         return dp[0][n - 1];
//     }
// }


// // dp O(n^2)time O(n^2)space
// class Solution {
//     public int longestPalindromeSubseq(String s) {
//         if (s == null || s.length() == 0) {
//             return 0;
//         }
        
//         int n = s.length();
//         char[] ss = s.toCharArray();
        
//         int[][] dp = new int[n][n];  // dp[i][j] represents the result of the subsequence betweenn i and j
        
//         for (int i = n - 1; i >= 0; i--) {
//             for (int j = i; j < n; j++) {
//                 if (i == j) {
//                     dp[i][j] = 1;
//                 } else if (i + 1 == j) {
//                     dp[i][j] = ss[i] == ss[j] ? 2 : 1;
//                 } else if (ss[i] == ss[j]) {
//                     dp[i][j] = 2 + dp[i + 1][j - 1];
//                 } else {
//                     dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
//                 }
//             }
//         }
        
//         return dp[0][n - 1];
//     }
// }


// dp O(n^2)time O(n)space
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] ss = s.toCharArray();
        
        int[] dp = new int[n];  // dp[i][j] represents the result of the subsequence betweenn i and j
        
        for (int i = n - 1; i >= 0; i--) {
            int prev = 0;
            for (int j = i; j < n; j++) {
                int temp = dp[j];
                if (i == j) {
                    dp[j] = 1;
                } else if (i + 1 == j) {
                    dp[j] = ss[i] == ss[j] ? 2 : 1;
                } else if (ss[i] == ss[j]) {
                    dp[j] = 2 + prev;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                
                prev = temp;
            }
        }
        
        return dp[n - 1];
    }
}